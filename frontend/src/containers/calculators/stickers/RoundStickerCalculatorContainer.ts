import {AbstractCalculator} from "../AbstractCalculator.js";
import {
    CalculationParams,
    CuttingType,
    Material,
    MaterialGroupType,
    MaterialType,
    PrintingProduct,
    ProductType,
    DOMRoundSticker
} from "./index.js";

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

    constructor() {
        super();
        let rootCnt = <HTMLDivElement>document.getElementById('calculator_root_cnt')
        rootCnt.innerHTML = DOMRoundSticker.part
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
        this.inputCutType.addEventListener("change", () => super.calculateProduct(this.createProduct()))
        this.inputMaterialType.addEventListener("change", () => super.calculateProduct(this.createProduct()))
        this.inputSize.addEventListener('input', (e) => {
            if (this.outputSize && this.inputSize) {
                this.outputSize.innerHTML = this.inputSize.value
            }
            super.calculateProduct(this.createProduct())
        })
        this.inputCount.addEventListener('input', () => {
            if (this.outputCount && this.inputCount && this.outputSizePreview && this.inputSize) {
                this.outputCount.innerHTML = this.inputCount.value
                this.outputSizePreview.innerHTML = this.inputSize.value
            }
            super.calculateProduct(this.createProduct())
        })
        super.initCalculatorFormParams(MaterialGroupType.SELF_ADHESIVE)
    }

    renderCalculatorForm(response: CalculationParams): void {
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
        this.calculateProduct(this.createProduct())
    }

    updateCalculatorFormParams(product: PrintingProduct): void {
        if (product.size && product.size.diameter && product.quantity && product.totalPrice &&
            product.productionTime && product.quantityPerSheet && this.outputSizePreview && this.inputCount &&
            this.outputCount && this.outputPrice && this.outputDate) {
            this.outputSizePreview.innerHTML = product.size.diameter.toString() + " мм"
            this.inputCount.step = product.quantityPerSheet.toString()
            this.outputCount.innerHTML = product.quantity.toString()
            this.outputPrice.innerHTML = product.totalPrice.toString()

            let options: Intl.DateTimeFormatOptions = {
                day: "numeric", month: "long",
                hour: "2-digit", minute: "2-digit"
            };
            this.outputDate.innerHTML = new Date(Date.parse(product.productionTime.toString()))
                .toLocaleDateString("uk-UA", options)
        }
    }

    createProduct(): PrintingProduct | undefined {
        if (this.inputCount && this.inputMaterialType && this.inputCutType && this.inputSize) {
            return {
                productType: ProductType.STICKER,
                quantity: Number.parseFloat(this.inputCount.value),
                material: {
                    materialType: MaterialType[this.inputMaterialType.value as keyof typeof MaterialType]
                },
                cuttingType: CuttingType[this.inputCutType.value as keyof typeof CuttingType],
                size: {
                    diameter: Number.parseFloat(this.inputSize.value),
                }
            }
        }
    }

    addToCart(): void {
    }
}