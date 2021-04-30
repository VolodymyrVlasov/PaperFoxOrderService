package com.paperfox.order.repositories.calculator;

import com.paperfox.order.fakeDB.MaterialsFakeDB;
import com.paperfox.order.fakeDB.TimeScopesFakeDB;
import com.paperfox.order.models.materials.Material;
import com.paperfox.order.models.materials.MaterialType;
import com.paperfox.order.models.materials.Price;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class FakeRepository { //implements IFakeCalculatorRepository
    public static List<Material> getAllMaterials() {
        return MaterialsFakeDB.materials;
    }

    public static List<Material> getMaterialsByMaterialGroupType(String groupType) {
        List<Material> groupMaterial = new ArrayList<>();

        for (Material material : getAllMaterials()) {
            if (material.getGroupType().name().equals(groupType)) {
                groupMaterial.add(material);
            }
        }
        return groupMaterial;
    }

    public static Material getMaterialByMaterialType(MaterialType materialType) {
        for (Material material : getAllMaterials()) {
            if (material.getMaterialType().name().equals(materialType.name())) {
                return material;
            }
        }
        return null;
    }


    public static Map<String, Integer> getFirstDeadlineTime() {
        Map<String, Integer> time = new HashMap<>();
        time.put("HOUR_OF_DAY", TimeScopesFakeDB.firstTimeHour);
        time.put("MINUTE", TimeScopesFakeDB.firstTimeMinute);
        return time;
    }

    public static Map<String, Integer> getSecondDeadlineTime() {
        Map<String, Integer> time = new HashMap<>();
        time.put("HOUR_OF_DAY", TimeScopesFakeDB.secondTimeHour);
        time.put("MINUTE", TimeScopesFakeDB.secondTimeMinute);
        return time;
    }
}
