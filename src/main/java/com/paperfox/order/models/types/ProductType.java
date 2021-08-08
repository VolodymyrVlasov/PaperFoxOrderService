package com.paperfox.order.models.types;

public enum ProductType {
    ROUND ("ROUND"),
    SQUARED ("SQUARED"),
    FIGURE  ("FIGURE"),
    STICKER_SET  ("STICKER_SET");

    public final String name;

    ProductType(String name) {
        this.name = name;
    }
}
