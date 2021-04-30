import {MaterialGroupType} from "./MaterialGroupType.js";
import {MaterialType} from "./MaterialType.js";

export interface Material {
    index?: number;
    name?: string;
    productionTime?: Date;
    groupType?: MaterialGroupType;
    materialType: MaterialType;
}