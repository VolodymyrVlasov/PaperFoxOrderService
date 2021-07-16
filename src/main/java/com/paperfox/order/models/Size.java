package com.paperfox.order.models;

public class Size {
    public double width;
    public double height;
    public double diameter;
    public double borderRadius;

    public Size(double width, double height, double diameter, double borderRadius) {
        this.width = width;
        this.height = height;
        this.diameter = diameter;
        this.borderRadius = borderRadius;
    }

    @Override
    public String toString() {
        return "Size{" +
                "width=" + width +
                ", height=" + height +
                ", diameter=" + diameter +
                ", borderRadius=" + borderRadius +
                '}';
    }
}
