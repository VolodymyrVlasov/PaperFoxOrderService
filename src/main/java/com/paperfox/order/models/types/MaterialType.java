package com.paperfox.order.models.types;

import com.paperfox.order.models.Size;

import static com.paperfox.order.constants.Price.*;
import static com.paperfox.order.constants.PrintSizes.*;

public enum MaterialType {
    RAFLATAC(RAFLATAC_PRINTABLE_SIZE, RAFLATAC_PRINT_PRICE, "Паперова наліпка"),
    RAFLATAC_LAMINATED(RAFLATAC_PRINTABLE_SIZE, RAFLATAC_LAMINATED_PRINT_PRICE, "Паперова наліпка з ламинацією"),
    RAFLATAC_PET(RAFLATAC_PRINTABLE_SIZE, RAFLATAC_PET_PRINT_PRICE, "PET наліпка"),
    RITRAMA_LAMINATED(RITRAMA_PRINTABLE_SIZE, RITRAMA_LAMINATED_PRINT_PRICE, "Вініл ламінований"),
    RITRAMA_TRANSPARENT(RITRAMA_PRINTABLE_SIZE, RITRAMA_TRANSPARENT_PRINT_PRICE, "Прозора наліпка"),
    UPM(SRA3_PRINTABLE_SIZE, UPM_PRINT_PRICE, "Каландрований папір"),
    COLOR_COPY(SRA3_PRINTABLE_SIZE, COLOR_COPY_PRINT_PRICE, "Крейдований папір");

    public final Size printableSize;
    public final int[] price;
    public final String name;

    MaterialType(Size printableSize, int[] price, String name) {
        this.printableSize = printableSize;
        this.price = price;
        this.name = name;
    }
}
