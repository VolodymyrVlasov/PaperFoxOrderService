import {AbstractCalculator} from "../AbstractCalculator.js";
import {MaterialGroupType} from "../../types/MaterialGroupType.js";
import {RoundStickerDOM} from "./RoundStickerDOM.js";
import {PrintingProduct} from "../../types/PrintingProduct.js";
import {ProductType} from "../../types/ProductType.js";
import {MaterialType} from "../../types/MaterialType.js";
import {CuttingType} from "../../types/CuttingType.js";
import {ApiConfig} from "../../api/ApiConfig.js";
import {OrderServiceApi} from "../../api/PaperFoxApi.js";
import {CalculationParams} from "../../types/CalculationParams.js";
import {Material} from "../../types/Material.js";

export class RoundStickerCalculatorContainer extends AbstractCalculator {
    //inputs
    inputSize: HTMLInputElement | undefined
    inputCount: HTMLInputElement | undefined
    inputMaterialType: HTMLInputElement | undefined
    inputCutType: HTMLInputElement | undefined
    //outputs
    outputSize: HTMLElement | undefined
    outputSizePreview: HTMLElement | undefined
    outputCount: HTMLElement | undefined
    outputPrice: HTMLElement | undefined
    outputDate: HTMLElement | undefined
    roundSticker?: PrintingProduct

    initCalculatorFormParams(materialGroupType: MaterialGroupType): void {
        super.initCalculatorFormParams(materialGroupType)
    }

    renderCalculatorForm(response: CalculationParams): void {
        console.log("render form method")
        let rootCnt = <HTMLDivElement>document.getElementById('calculator_root_cnt')
        rootCnt.innerHTML = RoundStickerDOM.part

        this.inputSize = <HTMLInputElement>document.getElementById("sticker_size_input")
        this.outputSizePreview = <HTMLElement>document.getElementById("sticker_preview_size_label")
        this.inputCount = <HTMLInputElement>document.getElementById("sticker_quantity_input")
        this.inputMaterialType = <HTMLInputElement>document.getElementById("sticker_material")
        this.inputCutType = <HTMLInputElement>document.getElementById("sticker_cutting_type")

        this.outputSize = <HTMLElement>document.getElementById("sticker_size_value")
        this.outputSize.innerHTML = this.inputSize.value;
        this.outputCount = <HTMLElement>document.getElementById("sticker_quantity_value")
        this.outputPrice = <HTMLElement>document.getElementById("sticker_result_price")
        this.outputDate = <HTMLElement>document.getElementById("sticker_result_date")

        this.inputCutType.addEventListener("change", () => this.calculateProduct())
        this.inputMaterialType.addEventListener("change", () => this.calculateProduct())
        this.inputSize.addEventListener('input', (e) => {
            if (this.outputSize && this.inputSize) {
                this.outputSize.innerHTML = this.inputSize.value
            }
            this.calculateProduct()
        })
        this.inputCount.addEventListener('input', () => {
            if (this.outputCount && this.inputCount && this.outputSizePreview && this.inputSize) {
                this.outputCount.innerHTML = this.inputCount.value
                this.outputSizePreview.innerHTML = this.inputSize.value
            }
            this.calculateProduct()
        })

        const materialType: Material[] = response.materialType
        const cuttingType: CuttingType = response.cuttingType

        let materialTypeSelect = <HTMLSelectElement>document.getElementById('sticker_material')
        let cuttingTypeMapSelect = <HTMLSelectElement>document.getElementById('sticker_cutting_type')
        if (cuttingTypeMapSelect == null || materialTypeSelect == null) return

        materialType.forEach((material) => {
            let option = document.createElement('option')
            if (material.name)
                option.innerText = material.name
            option.value = material.materialType
            materialTypeSelect.appendChild(option)
        })

        new Map(Object.entries(cuttingType)).forEach(function (value, key) {
            let option = document.createElement('option')
            option.innerText = value
            option.value = key
            cuttingTypeMapSelect.appendChild(option)
        });
        this.calculateProduct()
    }

    calculateProduct(): void {
        if (this.inputCount && this.inputMaterialType && this.inputCutType && this.inputSize)
            this.roundSticker = {
                productType: ProductType.STICKER,
                quantity: Number.parseFloat(this.inputCount.value),
                material: {
                    materialType: MaterialType[this.inputMaterialType.value as keyof typeof MaterialType]
                },
                cuttingType: CuttingType[this.inputCutType.value as keyof typeof CuttingType],
                size: {
                    diameter: Number.parseFloat(this.inputSize.value),
                }
            };

        let request: Request = new Request(
            `${ApiConfig.URL}calc`,
            {
                method: "POST",
                body: JSON.stringify(this.roundSticker),
                headers: {
                    "Content-Type": "application/json"
                }
            }
        )

        OrderServiceApi.restPostRequest<PrintingProduct>(request)
            .then((response) => {
                if (response.status != 200) {
                    throw new Error(`Server error: ${response.status} ${response.statusText} `)
                }
                if (response.jsonBody) {
                    this.updateCalculatorFormParams(response.jsonBody)
                } else {
                    throw new Error("Empty jsonBody")
                }
            })
            .catch((err) => console.log(err.toString()))
    }

    updateCalculatorFormParams(response: PrintingProduct): void {
        console.log("update calculator form method")
        if (response.size && response.size.diameter && response.quantity && response.totalPrice &&
            response.productionTime && response.quantityPerSheet && this.outputSizePreview && this.inputCount &&
            this.outputCount && this.outputPrice && this.outputDate) {
            this.outputSizePreview.innerHTML = response.size.diameter.toString() + " мм"
            this.inputCount.step = response.quantityPerSheet.toString()
            this.outputCount.innerHTML = response.quantity.toString()
            this.outputPrice.innerHTML = response.totalPrice.toString()

            let options: Intl.DateTimeFormatOptions = {
                day: "numeric", month: "long",
                hour: "2-digit", minute: "2-digit"
            };
            this.outputDate.innerHTML = new Date(Date.parse(response.productionTime.toString()))
                .toLocaleDateString("uk-UA", options)
        }
    }

    addToCart(): void {
    }

}