package com.paperfox.order.repositories.calculator;

import com.paperfox.order.models.Material;
import com.paperfox.order.models.MaterialGroupType;
import com.paperfox.order.models.types.MaterialGroups;

import java.util.List;

public interface IFakeCalculatorRepository {
    List<Material> getByGroupType(String groupType);
}
