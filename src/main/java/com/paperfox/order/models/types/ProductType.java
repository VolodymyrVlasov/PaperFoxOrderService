package com.paperfox.order.models.types;

public enum ProductType {
    STICKER("Наліпка"),
    BIZ_CARD("Візитівки"),
    FLYER("Флаєр"),
    POSTER ("Постер"),
    DRAWING ("Креслення"),
    PHOTO ("Фото"),
    MAGNET("Магніт");

    public final String name;

    ProductType(String name) {
        this.name = name;
    }
}
