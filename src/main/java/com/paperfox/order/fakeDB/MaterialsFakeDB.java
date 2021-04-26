package com.paperfox.order.fakeDB;

import com.paperfox.order.models.materials.Material;
import com.paperfox.order.models.materials.MaterialGroupType;
import com.paperfox.order.models.materials.MaterialType;

import java.util.ArrayList;
import java.util.List;

import static com.paperfox.order.fakeDB.PriceFakeDB.RAFLATAC_PRINT_PRICE;
import static com.paperfox.order.fakeDB.PrintSizesFakeDB.RAFLATAC_PRINTABLE_SIZE;

public class MaterialsFakeDB {
    public static final List<Material> materials = new ArrayList<>();

    {
        Material raflatacMaterial = new Material(
                1,
                "Паперова наліпка",
                RAFLATAC_PRINT_PRICE, RAFLATAC_PRINTABLE_SIZE,
                1,
                MaterialGroupType.SELF_ADHESIVE,
                MaterialType.RAFLATAC);

        Material raflatacLaminatedMaterial = new Material(
                2,
                "Паперова наліпка з ламинацією",
                RAFLATAC_PRINT_PRICE, RAFLATAC_PRINTABLE_SIZE,
                1,
                MaterialGroupType.SELF_ADHESIVE,
                MaterialType.RAFLATAC_LAMINATED);

        Material raflatacPETMaterial = new Material(
                3,
                "PET наліпка",
                RAFLATAC_PRINT_PRICE, RAFLATAC_PRINTABLE_SIZE,
                1,
                MaterialGroupType.SELF_ADHESIVE,
                MaterialType.RAFLATAC_PET);

        Material ritramaLaminatedMaterial = new Material(
                4,
                "Вініл ламінований",
                RAFLATAC_PRINT_PRICE, RAFLATAC_PRINTABLE_SIZE,
                2,
                MaterialGroupType.SELF_ADHESIVE,
                MaterialType.RITRAMA_LAMINATED);

        Material ritramaTransparentMaterial = new Material(
                5, "Прозора наліпка",
                RAFLATAC_PRINT_PRICE, RAFLATAC_PRINTABLE_SIZE,
                1,
                MaterialGroupType.SELF_ADHESIVE,
                MaterialType.RITRAMA_TRANSPARENT);

        materials.add(raflatacMaterial);
        materials.add(raflatacLaminatedMaterial);
        materials.add(raflatacPETMaterial);
        materials.add(ritramaLaminatedMaterial);
        materials.add(ritramaTransparentMaterial);
    }
}
