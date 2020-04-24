package com.hxy.jdk.stream;

public class MilestonesValidTO {
    private String id;

    private String message;

    private String orderNO;

    private String courierBillNo;

    private String status;

    public MilestonesValidTO(String id, String message, String orderNO, String courierBillNo) {
        super();
        this.id = id;
        this.message = message;
        this.orderNO = orderNO;
        this.courierBillNo = courierBillNo;
    }

    @Override
    public String toString() {
        return "MilestonesValidTO{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", orderNO='" + orderNO + '\'' +
                ", courierBillNo='" + courierBillNo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderNO() {
        return orderNO;
    }

    public void setOrderNO(String orderNO) {
        this.orderNO = orderNO;
    }

    public String getCourierBillNo() {
        return courierBillNo;
    }

    public void setCourierBillNo(String courierBillNo) {
        this.courierBillNo = courierBillNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
