// import {CalculatorContainer} from "./CalculatorContainer.js"; // привязка файла index.ts с экпортом типов к названию папки
import {RoundStickerCalculatorContainer} from "./RoundStickerCalculatorContainer.js";
import {MaterialGroupType} from "../../types/MaterialGroupType.js";

// new CalculatorContainer().init()

let roundStickerBtn: HTMLButtonElement = <HTMLButtonElement>document.getElementById('round_sticker_btn')
let squaredStickerBtn: HTMLButtonElement = <HTMLButtonElement>document.getElementById('squared_sticker_btn')
new RoundStickerCalculatorContainer().initCalculatorFormParams(MaterialGroupType.SELF_ADHESIVE)

roundStickerBtn.addEventListener('click', () => {
    new RoundStickerCalculatorContainer().initCalculatorFormParams(MaterialGroupType.SELF_ADHESIVE)
})

squaredStickerBtn.addEventListener('click', ()=>{
    // new SquaredStickerCalculator().initCalculatorFormParams(MaterialGroupType.SELF_ADHESIVE)
})


