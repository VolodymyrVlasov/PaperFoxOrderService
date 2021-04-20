package com.paperfox.order.models;

public class Size {
    public double width;
    public double height;
    public double diameter;
    public double borderRadius;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Size(int width, int height, int borderRadius) {
        this.width = width;
        this.height = height;
        this.borderRadius = borderRadius;
    }

    public Size(int diameter) {
        this.diameter = diameter;
    }
}
