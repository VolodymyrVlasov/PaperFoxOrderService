package com.paperfox.order.models.types;

import com.paperfox.order.models.Material;

// todo: add product Date calculation -> ?
//
// todo: add separate file upload -> ?

public enum MaterialGroups {

    SELF_ADHESIVE(MaterialTypes.selfAdhesive);

    public final Material[] material;

    MaterialGroups(Material[] material) {
        this.material = material;
    }
}


