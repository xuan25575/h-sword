package com.hxy.redis;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

/**
 * @Author huang_2
 * @Date 2020/4/27 2:06 下午
 * @Description TODO
 */

public class DispathLockByZk implements Lock, Watcher {

    private ZooKeeper zooKeeper;

    private String ROOTPATH = "/lock";

    private String current; //当前节点

    private String pre; //前一个节点


    public DispathLockByZk() {
        try {
            //创建zk的链接
            zooKeeper = new ZooKeeper("127.0.0.1:2181",500,this);
            Stat stat = zooKeeper.exists(ROOTPATH,false);
            //判断是否存在 /lock 的节点，用来存放在锁竞争过程中的数据
            if(stat == null){
                //不存在 /lock 创建
                zooKeeper.create(ROOTPATH,"0".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void lock() {

        if(tryLock()){
            System.out.println(Thread.currentThread().getName() +" 获取锁成功");
            return;
        }
        try {
            waitForLock();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
    //等待pre节点被删除 ==上一个获取锁的线程释放锁
    private void waitForLock() throws KeeperException, InterruptedException {
        Stat stat =  zooKeeper.exists(pre, true);
        if(stat !=null){
            System.out.println(Thread.currentThread().getName() + "正在等待");
            synchronized (pre){
                Long currentTime = System.currentTimeMillis();
                pre.wait(10000); //设置超时时间
                Long now = System.currentTimeMillis();

                if(now -  currentTime >= 10000){
                    System.out.println(Thread.currentThread().getName()+"超过等待时间，退出竞争");
                    unlock();
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "获取锁成功");
            }
        }


    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }
    @Override
    public boolean tryLock() {
        //创建临时有序的节点
        try {
            current =  zooKeeper.create(ROOTPATH+"/","0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(Thread.currentThread().getName() + "获取到的节点为"+current);
            List<String> list =  zooKeeper.getChildren(ROOTPATH,false); //当前所有的节点
            //对节点排序
            list =  list.stream().map(e -> {
                return ROOTPATH+"/"+e;
            }).sorted((a,b) -> {
                return a.compareTo(b);
            }).collect(Collectors.toList());

//            Collections.sort(list);
            //当前节点的序号
            int i =  list.indexOf(current);
            if(i == 0){
                return true; //拿到锁了
            }else{
                //记录上一个节点
                pre = list.get(i - 1) ;
                return false;
            }

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }
    @Override
    public void unlock() {
        System.out.println("释放锁");
        try {
            zooKeeper.delete(current,-1);
            current = null;
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Condition newCondition() {
        return null;
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        //由于监听的是上一个的节点，所以，当process被触发时，上一个线程释放了锁，所以本线程可以去获取锁了
        synchronized (pre){
            pre.notify();
        }
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for(int i = 0;i<10;i++){
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    DispathLockByZk dispathLock = new DispathLockByZk();
                    dispathLock.lock(); //获取锁
                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();
            countDownLatch.countDown();
        }
    }

}

