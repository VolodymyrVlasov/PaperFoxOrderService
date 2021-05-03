import {PrintingProduct} from "../types/PrintingProduct.js";
import {MaterialGroupType} from "../types/MaterialGroupType.js";
import {CalculationParams} from "../types/CalculationParams.js";

export interface IPaperFoxApi {
    calculateProduct(printingProduct: PrintingProduct): Promise<PrintingProduct>
    getInitParams(materialGroupType: MaterialGroupType): Promise<CalculationParams>
}
