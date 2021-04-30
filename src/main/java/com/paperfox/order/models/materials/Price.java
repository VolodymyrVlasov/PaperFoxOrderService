package com.paperfox.order.models.materials;

public class Price {
    int circulation;
    double price;

    public Price(int circulation, double price) {
        this.circulation = circulation;
        this.price = price;
    }

    public int getCirculation() {
        return circulation;
    }

    public void setCirculation(int circulation) {
        this.circulation = circulation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Price{" +
                "circulation=" + circulation +
                ", price=" + price +
                '}';
    }
}
