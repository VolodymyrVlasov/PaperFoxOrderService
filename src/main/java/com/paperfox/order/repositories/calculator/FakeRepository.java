package com.paperfox.order.repositories.calculator;

import com.paperfox.order.models.materials.Material;
import com.paperfox.order.models.materials.MaterialGroupType;
import com.paperfox.order.models.materials.MaterialType;

import java.util.ArrayList;
import java.util.List;

import static com.paperfox.order.constants.Price.RAFLATAC_PRINT_PRICE;
import static com.paperfox.order.constants.PrintSizes.RAFLATAC_PRINTABLE_SIZE;

//interface IFakeRepository {
//
//}

public class FakeCalculatorRepository implements IFakeCalculatorRepository {
    public static List<Material> getAllData() {
        List<Material> materials = new ArrayList<>();
        Material reflatac = new Material(
                1,
                "Паперова наліпка",
                RAFLATAC_PRINT_PRICE, RAFLATAC_PRINTABLE_SIZE,
                1,
                MaterialGroupType.SELF_ADHESIVE,
                MaterialType.RAFLATAC);

        materials.add(reflatac);

        Material reflatacLaminated = new Material(
                2,
                "Паперова наліпка з ламинацією",
                RAFLATAC_PRINT_PRICE, RAFLATAC_PRINTABLE_SIZE,
                1,
                MaterialGroupType.SELF_ADHESIVE,
                MaterialType.RAFLATAC_LAMINATED);

        materials.add(reflatacLaminated);

        Material raflatacPET = new Material(
                3,
                "PET наліпка",
                RAFLATAC_PRINT_PRICE, RAFLATAC_PRINTABLE_SIZE,
                1,
                MaterialGroupType.SELF_ADHESIVE,
                MaterialType.RAFLATAC_LAMINATED);
        materials.add(raflatacPET);

        Material ritramaLaminated = new Material(
                4,
                "Вініл ламінований",
                RAFLATAC_PRINT_PRICE, RAFLATAC_PRINTABLE_SIZE,
                2,
                MaterialGroupType.SELF_ADHESIVE,
                MaterialType.RAFLATAC_LAMINATED);
        materials.add(ritramaLaminated);

        Material ritramaTrasparent = new Material(
                5, "Прозора наліпка", RAFLATAC_PRINT_PRICE, RAFLATAC_PRINTABLE_SIZE, 1,
                MaterialGroupType.SELF_ADHESIVE,
                MaterialType.RAFLATAC_LAMINATED);
        materials.add(ritramaTrasperent);

        return materials;
    }

    @Override
    public List<Material> getByGroupType(String groupType) {
        return null;
    }


//    public static List<Material> getOfType(MaterialGroupType group) {
////        getAllData().sort();
//    }
}
