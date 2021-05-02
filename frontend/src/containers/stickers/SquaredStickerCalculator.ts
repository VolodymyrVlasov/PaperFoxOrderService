import {AbstractCalculator} from "../AbstractCalculator.js";
import {MaterialGroupType} from "../../types/MaterialGroupType.js";
import {CalculationParams} from "../../types/CalculationParams";

export class SquaredStickerCalculator extends AbstractCalculator {
    initCalculatorFormParams(materialGroupType: MaterialGroupType): void {
        super.initCalculatorFormParams(materialGroupType)
    }

    renderCalculatorForm(response: CalculationParams): void {
        console.log("render form method", response)
        // todo: create DOM content with
        let rootCnt = <HTMLDivElement> document.getElementById('calculator_root_cnt')
        rootCnt.innerHTML = ""
    }

    calculateProduct(): void {
    }

    updateCalculatorFormParams(): void {
        console.log("update calculator form method")

    }

    addToCart(): void {
    }

}