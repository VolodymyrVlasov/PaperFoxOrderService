import {Material} from "./Material.js";
import {Size} from "./Size.js";
import {CuttingType} from "./CuttingType.js";
import {ProductType} from "./ProductType.js";

export interface PrintingProduct {
    quantity: number,
    quantityPerSheet?: number,
    material: Material,
    size: Size | null,
    cuttingType: CuttingType
    totalPrice?: number
    productType: ProductType
    productionTime?: Date
}