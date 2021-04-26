package com.paperfox.order.models;

public class ProductSize extends Size {

    public ProductSize(double width, double height, double diameter, double borderRadius) {
        super(width, height, diameter, borderRadius);
    }

    @Override
    public String toString() {
        return "ProductSize{" +
                "width=" + width +
                ", height=" + height +
                ", diameter=" + diameter +
                ", borderRadius=" + borderRadius +
                "}";
    }
}
