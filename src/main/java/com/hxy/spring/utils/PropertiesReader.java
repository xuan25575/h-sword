package com.hxy.spring.utils;

/**
 * @Author huang_2
 * @Date 2020/4/25 8:10 下午
 *
 * 基于ResourceLoader读取Properties配置文件
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private static final Logger log = LoggerFactory.getLogger(PropertiesReader.class);


    /**
     * IP和端口号<或域名>
     */
    public static String WX_BASE_PATH = "";

    /**
     * 配置文件
     */
    public static Properties p;

    private static ResourceLoader resourceLoader = new DefaultResourceLoader();

    /**
     * 属性文件加载对象
     */
    // private static PropertiesLoader propertiesLoader = new
    // PropertiesLoader("resource/config.properties");

    static {
        InputStream in = null;
        try {
            Resource resource = resourceLoader.getResource("config/cloudpayment.properties");
            in = resource.getInputStream();
            // in = new BufferedInputStream(newFileInputStream("resource/config.properties"));
            p = new Properties();
            p.load(in);

            /**IP和端口号*/
            WX_BASE_PATH = p.getProperty("wx.basePath").trim();

        } catch (Exception e) {
            log.error("读取配置文件失败", e);
        }
    }

    public static void main(String[] args) {


        System.out.println(WX_BASE_PATH);
        System.out.println("DL_CLOUD_APIURL:"+p.getProperty("dl.cloud.apiurl").trim());

    }
}

