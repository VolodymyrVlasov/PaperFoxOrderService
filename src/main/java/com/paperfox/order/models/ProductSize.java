package com.paperfox.order.models;

public class ProductSize extends Size {

    public ProductSize(double width, double height, double diameter, double borderRadius) {
        super(width, height, diameter, borderRadius);
    }

    @Override
    public String toString() {
        return "ProductSize{" +
                "\n\t\twidth=" + width +
                ", \n\t\theight=" + height +
                ", \n\t\tdiameter=" + diameter +
                ", \n\t\tborderRadius=" + borderRadius +
                "\n\t}";
    }
}
