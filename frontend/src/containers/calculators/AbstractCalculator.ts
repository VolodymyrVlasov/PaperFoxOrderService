import {MaterialGroupType} from "../../types/MaterialGroupType.js";
import {Api} from "../../api/PaperFoxApi.js";
import {CalculationParams} from "../../types/CalculationParams.js";
import {PrintingProduct} from "../../types/PrintingProduct.js";

export abstract class AbstractCalculator {


    abstract renderCalculatorForm(response: CalculationParams): void

    abstract updateCalculatorFormParams(response: PrintingProduct): void


    abstract addToCart(): void

    abstract createProduct(): PrintingProduct | undefined

    public calculateProduct(printingProduct: PrintingProduct | undefined): void {
        if (printingProduct)
            Api.calculateProduct(printingProduct)
                .then(response => this.updateCalculatorFormParams(response))
                .catch(er => console.log(er))
    }

    public initCalculatorFormParams(materialGroupType: MaterialGroupType): void {
        Api.getInitParams(materialGroupType)

            .then(response => this.renderCalculatorForm(response))
            .catch(er => console.log(er))
    }
}