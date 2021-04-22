package com.paperfox.order.models.params;

import com.paperfox.order.models.types.CuttingType;
import com.paperfox.order.models.types.MaterialType;

import java.util.List;
import java.util.Map;

public class CalculatorParams {
    Map<String, String> cuttingType;
    Map<String, String> materialType;

    public CalculatorParams(Map<String, String> materialType, Map<String, String> cuttingType) {
        this.cuttingType = cuttingType;
        this.materialType = materialType;
    }

    public Map<String, String> getCuttingType() {
        return cuttingType;
    }

    public Map<String, String> getMaterialType() {
        return materialType;
    }
}