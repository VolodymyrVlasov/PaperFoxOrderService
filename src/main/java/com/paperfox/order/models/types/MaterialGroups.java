package com.paperfox.order.models.types;

import com.paperfox.order.models.Material;

// todo: separate materials to object
// todo: add product data calculation
//
// todo: add separate file upload ?

public enum MaterialGroups {

    SELF_ADHESIVE(MaterialTypes.selfAdhesive);

    public final Material[] material;

    MaterialGroups(Material[] material) {
        this.material = material;
    }
}


