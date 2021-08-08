package com.paperfox.order.models.types;

public enum ProductGroupType {
    STICKER("Наліпка"),
    BIZ_CARD("Візитівки"),
    FLYER("Флаєр"),
    POSTER ("Постер"),
    DRAWING ("Креслення"),
    PHOTO ("Фото"),
    MAGNET("Магніт");

    public final String name;

    ProductGroupType(String name) {
        this.name = name;
    }
}
