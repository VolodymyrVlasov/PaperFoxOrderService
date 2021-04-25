package com.paperfox.order.repositories.calculator;

import com.paperfox.order.models.Material;
import com.paperfox.order.models.MaterialGroupType;

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
        Material reflatic = new Material(
                1, "Паперова наліпка", RAFLATAC_PRINT_PRICE, RAFLATAC_PRINTABLE_SIZE, 1);
        materials.add(reflatic);

        Material reflaticLaminated = new Material(
                2, "Паперова наліпка з ламинацією", RAFLATAC_PRINT_PRICE, RAFLATAC_PRINTABLE_SIZE, 1);
        materials.add(reflaticLaminated);

        Material RAFLATAC_PET = new Material(
                3, "PET наліпка", RAFLATAC_PRINT_PRICE, RAFLATAC_PRINTABLE_SIZE, 1);
        materials.add(RAFLATAC_PET);
        return materials;
//        Material RITRAMA_LAMINATED = new Material(
//                4, "Вініл ламінований", RAFLATAC_PRINT_PRICE, RAFLATAC_PRINTABLE_SIZE, 2);
//        Material RITRAMA_TRANSPARENT = new Material(
//                5, "Прозора наліпка", RAFLATAC_PRINT_PRICE, RAFLATAC_PRINTABLE_SIZE, 1);
    }

    @Override
    public List<Material> getByGroupType(String groupType) {
        return null;
    }


//    public static List<Material> getOfType(MaterialGroupType group) {
////        getAllData().sort();
//    }
}
