import {MaterialGroupType} from "../types/MaterialGroupType.js";
import {OrderServiceApi} from "../api/PaperFoxApi.js";
import {CalculationParams} from "../types/CalculationParams.js";
import {PrintingProduct} from "../types/PrintingProduct.js";

export abstract class AbstractCalculator {
    initCalculatorFormParams(materialGroupType: MaterialGroupType): void {
        OrderServiceApi.restGetRequest<CalculationParams>(materialGroupType)
            .then(response => {
                if (response.status != 200) {
                    throw new Error(`Server error: ${response.status} ${response.statusText} `)
                }
                if (response.jsonBody) {
                    this.renderCalculatorForm(response.jsonBody)
                } else {
                    throw new Error("Empty jsonBody")
                }
            })
            .catch(error => console.log(error))
    }

    abstract renderCalculatorForm(response: CalculationParams): void

    abstract updateCalculatorFormParams(response: PrintingProduct): void

    abstract calculateProduct(): void

    abstract addToCart(): void

}