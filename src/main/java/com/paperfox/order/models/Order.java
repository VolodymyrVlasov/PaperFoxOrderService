package com.paperfox.order.models;

import com.paperfox.order.fakeDB.OrderId;
import com.paperfox.order.models.products.PrintingProduct;
import com.paperfox.order.models.types.DeliveryMethodType;
import com.paperfox.order.models.types.OrderStatusType;
import com.paperfox.order.models.types.PaymentMethodType;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

public class Order { //implements Validator
    private static final List<String> fileTypes = Arrays.asList(
            "application/pdf", "image/png", "image/jpg", "image/jpeg");
    private UUID orderUUID;
    private int orderID;
    private Customer customer;
    private DeliveryMethodType deliveryMethodType;
    private PaymentMethodType paymentMethodType;
    private OrderStatusType orderStatusType;
    private String orderPath;
    private GregorianCalendar timeStamp;
    private List<PrintingProduct> products;


    public Order(UUID orderUUID, int orderID, Customer customer, DeliveryMethodType deliveryMethodType,
                 PaymentMethodType paymentMethodType, OrderStatusType orderStatusType, String orderPath,
                 GregorianCalendar timeStamp, List<PrintingProduct> products) {
        this.orderUUID = orderUUID;
        this.orderID = orderID;
        this.customer = customer;
        this.deliveryMethodType = deliveryMethodType;
        this.paymentMethodType = paymentMethodType;
        this.orderStatusType = orderStatusType;
        this.orderPath = orderPath;
        this.timeStamp = timeStamp;
        this.products = products;
    }

    public UUID getOrderUUID() {
        return orderUUID;
    }

    public void setOrderUUID(UUID orderUUID) {
        this.orderUUID = orderUUID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public DeliveryMethodType getDeliveryMethodType() {
        return deliveryMethodType;
    }

    public void setDeliveryMethodType(DeliveryMethodType deliveryMethodType) {
        this.deliveryMethodType = deliveryMethodType;
    }

    public PaymentMethodType getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(PaymentMethodType paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    public OrderStatusType getOrderStatusType() {
        return orderStatusType;
    }

    public void setOrderStatusType(OrderStatusType orderStatusType) {
        this.orderStatusType = orderStatusType;
    }

    public String getOrderPath() {
        return orderPath;
    }

    public void setOrderPath(String orderPath) {
        this.orderPath = orderPath;
    }

    public GregorianCalendar getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(GregorianCalendar timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<PrintingProduct> getProducts() {
        return products;
    }

    public void setProducts(List<PrintingProduct> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderUUID=" + orderUUID +
                ", orderID=" + orderID +
                ", customer=" + customer +
                ", deliveryMethodType=" + deliveryMethodType +
                ", paymentMethodType=" + paymentMethodType +
                ", orderStatusType=" + orderStatusType +
                ", orderPath='" + orderPath + '\'' +
                ", timeStamp=" + timeStamp +
                ", products=" + products +
                '}';
    }
}
