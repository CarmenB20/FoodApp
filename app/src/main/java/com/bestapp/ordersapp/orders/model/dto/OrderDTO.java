package com.bestapp.ordersapp.orders.model.dto;

public class OrderDTO {

    private int orderNo;
    private String orderDescription;
    private String info;
    private long restaurantId;

    public OrderDTO(){
        super();
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderNo=" + orderNo +
                ", orderDescription='" + orderDescription + '\'' +
                ", info='" + info + '\'' +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
