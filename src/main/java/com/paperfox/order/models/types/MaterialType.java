package com.paperfox.order.models.types;

import com.paperfox.order.models.Size;
import static com.paperfox.order.constants.Price.*;
import static com.paperfox.order.constants.PrintSizes.*;

public enum MaterialType {
    RAFLATAC(RAFLATAC_PRINTABLE_SIZE, RAFLATAC_PRINT_PRICE),
    RAFLATAC_LAMINATED(RAFLATAC_PRINTABLE_SIZE, RAFLATAC_LAMINATED_PRINT_PRICE),
    RAFLATAC_PET(RAFLATAC_PRINTABLE_SIZE, RAFLATAC_PET_PRINT_PRICE),
    RITRAMA_LAMINATED(RITRAMA_PRINTABLE_SIZE, RITRAMA_LAMINATED_PRINT_PRICE),
    RITRAMA_TRANSPARENT(RITRAMA_PRINTABLE_SIZE, RITRAMA_TRANSPARENT_PRINT_PRICE),
    UPM(SRA3_PRINTABLE_SIZE, UPM_PRINT_PRICE),
    COLOR_COPY(SRA3_PRINTABLE_SIZE, COLOR_COPY_PRINT_PRICE);

    public final Size printableSize;
    public final int[] price;

    MaterialType(Size printableSize, int[] price) {
        this.printableSize = printableSize;
        this.price = price;
    }
}
