package com.paperfox.order.models.params;

import com.paperfox.order.models.materials.Material;

import java.util.List;
import java.util.Map;

public class CalculatorParams {
    Map<String, Integer> cuttingType;
    List<Material> materialType;

    public CalculatorParams(List<Material> materialType, Map<String, Integer> cuttingType) {
        this.cuttingType = cuttingType;
        this.materialType = materialType;
    }

    public Map<String, Integer> getCuttingType() {
        return cuttingType;
    }

    public List<Material> getMaterialType() {
        return materialType;
    }
}
