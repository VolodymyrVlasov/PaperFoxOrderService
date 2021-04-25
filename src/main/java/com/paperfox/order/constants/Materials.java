package com.paperfox.order.constants;

import com.paperfox.order.models.Material;

import static com.paperfox.order.constants.Price.RAFLATAC_PRINT_PRICE;
import static com.paperfox.order.constants.PrintSizes.RAFLATAC_PRINTABLE_SIZE;

public class Materials {
    public static final Material RAFLATAC = new Material(
            1, "Паперова наліпка", RAFLATAC_PRINT_PRICE, RAFLATAC_PRINTABLE_SIZE, 1);
    public static final Material RAFLATAC_LAMINATED = new Material(
            2, "Паперова наліпка з ламинацією", RAFLATAC_PRINT_PRICE, RAFLATAC_PRINTABLE_SIZE, 1);
    public static final Material RAFLATAC_PET = new Material(
            3, "PET наліпка", RAFLATAC_PRINT_PRICE, RAFLATAC_PRINTABLE_SIZE, 1);
    public static final Material RITRAMA_LAMINATED = new Material(
            4, "Вініл ламінований", RAFLATAC_PRINT_PRICE, RAFLATAC_PRINTABLE_SIZE, 2);
    public static final Material RITRAMA_TRANSPARENT = new Material(
            5, "Прозора наліпка", RAFLATAC_PRINT_PRICE, RAFLATAC_PRINTABLE_SIZE, 1);
}
