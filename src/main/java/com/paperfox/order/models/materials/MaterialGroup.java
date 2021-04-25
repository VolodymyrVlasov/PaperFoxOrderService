package com.paperfox.order.models.types;

import com.paperfox.order.models.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.paperfox.order.constants.Price.RAFLATAC_PRINT_PRICE;
import static com.paperfox.order.constants.PrintSizes.RAFLATAC_PRINTABLE_SIZE;

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


enum MaterialType {
    RAFLATAC,
    RAFLATAC_LAMINATED,
    RAFLATAC_PET,
    RITRAMA_LAMINATED,
    RITRAMA_TRANSPARENT,
}

//group:
//    List<Material>
//    type: Enum

// todo: -> public

enum MaterialGroupType {
    SELF_ADHESIVE,
}

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