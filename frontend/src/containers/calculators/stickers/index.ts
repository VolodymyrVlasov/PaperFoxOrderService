// import {CalculatorContainer} from "./CalculatorContainer.js"; // привязка файла index.ts с экпортом типов к названию папки
import {RoundStickerCalculatorContainer} from "./RoundStickerCalculatorContainer.js";
import {SquaredStickerCalculator} from "./SquaredStickerCalculator.js";
import {AbstractCalculator} from "../AbstractCalculator.js";
import {PrintingProduct} from "../../../types/PrintingProduct.js";
import {ProductType} from "../../../types/ProductType.js";
import {MaterialType} from "../../../types/MaterialType.js";
import {CuttingType} from "../../../types/CuttingType.js";
import {CalculationParams} from "../../../types/CalculationParams.js";
import {Material} from "../../../types/Material.js";
import {MaterialGroupType} from "../../../types/MaterialGroupType.js";
import {DOMSquaredSticker} from "./dom/DOMSquaredSticker.js";
import {DOMRoundSticker} from "./dom/DOMRoundSticker.js";
import { DOMCalculatorResult } from "./dom/DOMCalculatorResult.js";


export {
    AbstractCalculator,
    PrintingProduct,
    ProductType,
    MaterialType,
    CuttingType,
    CalculationParams,
    Material,
    MaterialGroupType,
    DOMSquaredSticker,
    DOMRoundSticker,
    DOMCalculatorResult
}

let roundStickerBtn: HTMLButtonElement = <HTMLButtonElement>document.getElementById('round_sticker_btn')
let squaredStickerBtn: HTMLButtonElement = <HTMLButtonElement>document.getElementById('squared_sticker_btn')

roundStickerBtn.addEventListener('click', () => {
    new RoundStickerCalculatorContainer()
})

squaredStickerBtn.addEventListener('click', () => {
    new SquaredStickerCalculator()
})
new RoundStickerCalculatorContainer()



