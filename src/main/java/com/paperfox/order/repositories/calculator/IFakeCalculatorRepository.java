package com.paperfox.order.repositories.calculator;

import com.paperfox.order.models.materials.Material;

import java.util.List;

public interface IFakeCalculatorRepository {
    List<Material> getByGroupType(String groupType);
}
