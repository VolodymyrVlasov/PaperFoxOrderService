package com.paperfox.order.services.calculator;

import com.paperfox.order.models.Material;
import com.paperfox.order.models.params.CalculatorParams;
import com.paperfox.order.models.types.CuttingType;
import com.paperfox.order.models.types.MaterialGroups;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OptionsCalculatorParams {

    public CalculatorParams getSelfAdhesiveCalculatorParams() {
        Map<String, String> cuttingType = new HashMap<>();
        List<Material> materialType = new ArrayList<>(Arrays.asList(MaterialGroups.SELF_ADHESIVE.material));

        for (CuttingType e : CuttingType.values()) {
            cuttingType.put(e.name(), e.name);
        }

        return new CalculatorParams(materialType, cuttingType);
    }
}
