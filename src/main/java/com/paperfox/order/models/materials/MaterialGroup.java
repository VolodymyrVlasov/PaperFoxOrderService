package com.paperfox.order.models.materials;

import java.util.List;

class MaterialGroup {
    private MaterialGroupType type;
    private List<Material> materials;

    // todo: add calc params to each group ?

    //  private Map<MaterialGroupType, List<Material>> mGroup;

    public MaterialGroup(MaterialGroupType type) {
        this.type = type;
    }

    public boolean materialTypeIfOfGroup(MaterialGroupType type) {
        // todo
        return true;
    }
}