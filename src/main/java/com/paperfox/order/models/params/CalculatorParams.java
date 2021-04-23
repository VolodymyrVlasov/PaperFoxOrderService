package com.paperfox.order.models.params;

import com.paperfox.order.models.Material;

import java.util.List;
import java.util.Map;

public class CalculatorParams {
    Map<String, String> cuttingType;
    List<Material> materialType;

    public CalculatorParams(List<Material> materialType, Map<String, String> cuttingType) {
        this.cuttingType = cuttingType;
        this.materialType = materialType;
    }

    public Map<String, String> getCuttingType() {
        return cuttingType;
    }

    public List<Material> getMaterialType() {
        return materialType;
    }
}
