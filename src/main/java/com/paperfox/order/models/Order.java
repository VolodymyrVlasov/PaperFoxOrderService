package com.paperfox.order.models;

import com.paperfox.order.controllers.OrderController;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public class Order  { //implements Validator
    private UUID orderUUID;
    private long orderID;
    private Customer customer;
    private DeliveryMethodType deliveryMethodType;
    private PaymentMethodType paymentMethodType;
    private OrderStatusType orderStatusType;
    private MultipartFile file;
    private GregorianCalendar timeStamp;

    private static final List<String> fileTypes = Arrays.asList(
            "application/pdf", "image/png", "image/jpg", "image/jpeg");

    public Order(Customer customer, DeliveryMethodType deliveryMethodType, PaymentMethodType paymentMethodType, OrderStatusType orderStatusType, GregorianCalendar timeStamp) {
        this.customer = customer;
        this.deliveryMethodType = deliveryMethodType;
        this.paymentMethodType = paymentMethodType;
        this.orderStatusType = orderStatusType;
        this.timeStamp = timeStamp;
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
                ",\n\ttimeStamp=" + timeStamp +
                '}';
    }
}
