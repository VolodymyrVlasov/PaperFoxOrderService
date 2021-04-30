import {Material} from "./Material.js";
import {CuttingType} from "./CuttingType.js";

export interface CalculationParams {
    materialType: Material[]
    cuttingType: CuttingType
}
