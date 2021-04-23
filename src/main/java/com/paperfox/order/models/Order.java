package com.paperfox.order.models;

import com.paperfox.order.models.types.DeliveryMethodType;
import com.paperfox.order.models.types.OrderStatusType;
import com.paperfox.order.models.types.PaymentMethodType;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

public class Order { //implements Validator
    private static final List<String> fileTypes = Arrays.asList(
            "application/pdf", "image/png", "image/jpg", "image/jpeg");
    private UUID orderUUID;
    private final long orderID;
    private final Customer customer;
    private final DeliveryMethodType deliveryMethodType;
    private final PaymentMethodType paymentMethodType;
    private final OrderStatusType orderStatusType;
    private MultipartFile file;
    private final GregorianCalendar timeStamp;

    public Order(Customer customer, DeliveryMethodType deliveryMethodType, PaymentMethodType paymentMethodType, OrderStatusType orderStatusType) {
        this.customer = customer;
        this.deliveryMethodType = deliveryMethodType;
        this.paymentMethodType = paymentMethodType;
        this.orderStatusType = orderStatusType;
        this.timeStamp = new GregorianCalendar();
        this.orderID = new Random().nextInt(500);
    }

    //    @Override
//    public boolean supports(Class<?> aClass) {
//        return Order.class.isAssignableFrom(aClass);
//    }

//    @Override
//    public void validate(Object o, Errors errors) {
//        Order order = (Order) o;
//        if (order.deliveryMethodType == null) {
//            errors.rejectValue("order.deliveryMethodType", "is empty");
//        }
//        if (order.paymentMethodType == null) {
//            errors.rejectValue("order.paymentMethodType", "is empty");
//        }
//        if (order.file != null) {
//            errors.rejectValue("order.deliveryMethodType", "is empty");
//            String fileType = order.file.getContentType();
//
//            boolean isOk = false;
//
//            for (String e : fileTypes) {
//                if (fileType.contains(e)) {
//                    isOk = true;
//                    break;
//                }
//                errors.rejectValue("order.file", "file type not support");
//            }
//        }
//    }


//    public UUID getOrderUUID() {
//        return orderUUID;
//    }

    public static List<String> getFileTypes() {
        return fileTypes;
    }

    public long getOrderID() {
        return orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public DeliveryMethodType getDeliveryMethodType() {
        return deliveryMethodType;
    }

    public PaymentMethodType getPaymentMethodType() {
        return paymentMethodType;
    }

    public OrderStatusType getOrderStatusType() {
        return orderStatusType;
    }

    public MultipartFile getFile() {
        return file;
    }

    public GregorianCalendar getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "\nOrder{" +
                "\n\torderUUID=" + orderUUID +
                ",\n\torderID=" + orderID +
                ",\n\tcustomer=" + customer +
                ",\n\tdeliveryMethodType=" + deliveryMethodType +
                ",\n\tpaymentMethodType=" + paymentMethodType +
                ",\n\torderStatusType=" + orderStatusType +
                ",\n\tfile=" + file +
                ",\n\ttimeStamp=" + timeStamp.getTime() +
                '}';
    }
}
