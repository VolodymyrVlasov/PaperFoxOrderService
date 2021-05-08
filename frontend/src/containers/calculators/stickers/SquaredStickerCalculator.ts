import {AbstractCalculator} from "../AbstractCalculator.js";
import {
    CalculationParams,
    CuttingType,
    DOMSquaredSticker,
    Material,
    MaterialGroupType,
    MaterialType,
    PrintingProduct,
    ProductType
} from "./index.js";
import {Api} from "../../../api/PaperFoxApi.js";


export class SquaredStickerCalculator extends AbstractCalculator {
    //inputs
    inputSizeWidth: HTMLInputElement | undefined
    inputSizeHeight: HTMLInputElement | undefined
    inputSizeCornerRadius: HTMLInputElement | undefined
    inputCount: HTMLInputElement | undefined
    inputMaterialType: HTMLInputElement | undefined
    inputCutType: HTMLInputElement | undefined
    //outputs
    outputSizePreviewWidth: HTMLElement | undefined
    outputSizePreviewHeight: HTMLElement | undefined
    outputSizePreviewCornerRadius: HTMLElement | undefined
    outputSizeCornerRadius: HTMLElement | undefined
    outputCount: HTMLElement | undefined
    outputPrice: HTMLElement | undefined
    outputDate: HTMLElement | undefined
    //file
    file: HTMLInputElement | undefined

    //preview
    preview: HTMLElement | undefined
    roundSticker?: PrintingProduct

    constructor() {
        super();
        let rootCnt = <HTMLDivElement>document.getElementById('calculator_root_cnt')
        rootCnt.innerHTML = DOMSquaredSticker.part

        this.file = <HTMLInputElement>document.getElementById('file')

        this.inputSizeWidth = <HTMLInputElement>document.getElementById("input_size_width")
        this.inputSizeHeight = <HTMLInputElement>document.getElementById("input_size_height")
        this.inputSizeCornerRadius = <HTMLInputElement>document.getElementById("input_size_corner_radius")
        this.inputCount = <HTMLInputElement>document.getElementById("sticker_quantity_input")

        this.inputMaterialType = <HTMLInputElement>document.getElementById("sticker_material")
        this.inputCutType = <HTMLInputElement>document.getElementById("sticker_cutting_type")

        this.outputSizePreviewWidth = <HTMLElement>document.getElementById("output_size_preview_width")
        this.outputSizePreviewWidth.innerHTML = this.inputSizeWidth.value + " мм"
        this.outputSizePreviewHeight = <HTMLElement>document.getElementById("output_size_preview_height")
        this.outputSizePreviewHeight.innerHTML = this.inputSizeHeight.value + " мм"
        this.outputSizePreviewCornerRadius = <HTMLElement>document.getElementById("output_size_preview_corner_radius")
        this.outputSizePreviewCornerRadius.innerHTML = this.inputSizeCornerRadius.value
        this.outputSizeCornerRadius = <HTMLElement>document.getElementById("output_size_corner_radius")
        this.outputSizeCornerRadius.innerHTML = this.inputSizeCornerRadius.value + " мм"

        this.outputCount = <HTMLElement>document.getElementById("sticker_quantity_value")
        this.outputPrice = <HTMLElement>document.getElementById("sticker_result_price")
        this.outputDate = <HTMLElement>document.getElementById("sticker_result_date")

        this.preview = <HTMLElement>document.getElementById("file_preview_cnt")

        this.file.addEventListener('change', (e) => {
            this.uploadFile(this.file?.files)
        })

        this.inputCutType.addEventListener("change", () => super.calculateProduct(this.createProduct()))
        this.inputMaterialType.addEventListener("change", () => super.calculateProduct(this.createProduct()))

        this.inputSizeWidth.addEventListener('input', (e) => {
            this.updatePreview()
            super.calculateProduct(this.createProduct())
        })
        this.inputSizeHeight.addEventListener('input', (e) => {
            this.updatePreview()
            super.calculateProduct(this.createProduct())
        })
        this.inputSizeCornerRadius.addEventListener('input', (e) => {
            this.updatePreview()
            super.calculateProduct(this.createProduct())
        })
        this.inputCount.addEventListener('input', () => {
            if (this.outputCount && this.inputCount) {
                this.outputCount.innerHTML = this.inputCount.value
            }
            super.calculateProduct(this.createProduct())
        })
        this.updatePreview()
        super.initCalculatorFormParams(MaterialGroupType.SELF_ADHESIVE)
    }

    updatePreview(): void {
        if (this.outputSizeCornerRadius && this.outputSizePreviewWidth && this.outputSizePreviewHeight && this.outputSizePreviewCornerRadius &&
            this.inputSizeWidth && this.inputSizeHeight && this.inputSizeCornerRadius) {
            this.outputSizePreviewWidth.innerHTML = this.inputSizeWidth.value + " мм"
            this.outputSizePreviewHeight.innerHTML = this.inputSizeHeight.value + " мм"
            this.outputSizePreviewCornerRadius.innerHTML = this.inputSizeCornerRadius.value
            this.outputSizeCornerRadius.innerHTML = this.inputSizeCornerRadius.value
        }

        if (this.preview && this.inputSizeCornerRadius) {
            let width: number = this.inputSizeWidth?.value != undefined ? Number.parseInt(this.inputSizeWidth?.value) : 1
            let height: number = this.inputSizeHeight?.value != undefined ? Number.parseInt(this.inputSizeHeight?.value) : 1
            let cornerRadius: number = this.inputSizeCornerRadius?.value != undefined ? Number.parseInt(this.inputSizeCornerRadius?.value) : 0

            /** calculate smaller size in percent with proportion between two sides for smallest side
             *  calculate border radius from smallest side
             */
            let previewImageCnt = document?.getElementById('drag_drop_area')
            let cutPreview = document?.getElementById('file_preview_cnt_input_wraper_drop_area_design_cut')
            this.preview.style.transitionDuration = "0.4s"
            this.inputSizeCornerRadius.max = String(Math.floor(width / 2))
            if (width == height) {
                this.preview.style.height = `100%`
                this.preview.style.width = `${this.preview.offsetHeight}px`
                if (previewImageCnt && cutPreview) {
                    let borderRadius = String(this.map(cornerRadius, this.preview.offsetWidth / 2, width / 2) + 'px')
                    previewImageCnt.style.borderRadius = cutPreview.style.borderRadius = borderRadius
                }
            } else if (width > height) {
                let k = width / height
                this.preview.style.width = `100%`
                this.preview.style.height = `${(this.preview.offsetWidth / k) > 80 ? Math.ceil(this.preview.offsetWidth / k) : 80}px`
                this.inputSizeCornerRadius.max = String(Math.floor(height / 2))
                if (previewImageCnt && cutPreview) {
                    let borderRadius = String(this.map(cornerRadius, this.preview.offsetHeight / 2, height / 2) + 'px')
                    previewImageCnt.style.borderRadius = cutPreview.style.borderRadius = borderRadius
                }
            } else {
                let k = height / width
                this.preview.style.height = `100%`
                this.preview.style.width = `${this.preview.offsetHeight / k > 80 ? Math.ceil(this.preview.offsetHeight / k) : 80}px`
                if (previewImageCnt && cutPreview) {
                    let borderRadius = String(this.map(cornerRadius, this.preview.offsetWidth / 2, width / 2) + 'px')
                    previewImageCnt.style.borderRadius = cutPreview.style.borderRadius = borderRadius
                }
            }
        }
    }

    map(inputCornerRadius: number, maxCornerRadius: number, inputMax: number): number {
        return inputCornerRadius * maxCornerRadius / inputMax;
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
        })
        this.calculateProduct(this.createProduct())
    }

    updateCalculatorFormParams(product: PrintingProduct): void {
        if (product.quantity && product.totalPrice &&
            product.productionTime && product.quantityPerSheet && this.inputCount &&
            this.outputCount && this.outputPrice && this.outputDate) {

            this.inputCount.step = product.quantityPerSheet.toString()
            this.inputCount.min = product.quantityPerSheet.toString()
            this.outputCount.innerHTML = product.quantity.toString()
            this.outputPrice.innerHTML = product.totalPrice.toString()

            let options: Intl.DateTimeFormatOptions = {
                day: "numeric", month: "long",
                hour: "2-digit", minute: "2-digit"
            }

            this.outputDate.innerHTML = new Date(Date.parse(product.productionTime.toString()))
                .toLocaleDateString("uk-UA", options)
        }
    }

    createProduct(): PrintingProduct | undefined {
        if (this.inputSizeHeight && this.inputSizeCornerRadius && this.inputCount && this.inputMaterialType && this.inputCutType && this.inputSizeWidth) {
            return {
                productType: ProductType.STICKER,
                quantity: Number.parseFloat(this.inputCount.value),
                material: {
                    materialType: MaterialType[this.inputMaterialType.value as keyof typeof MaterialType]
                },
                cuttingType: CuttingType[this.inputCutType.value as keyof typeof CuttingType],
                size: {
                    width: Number.parseFloat(this.inputSizeWidth.value),
                    height: Number.parseFloat(this.inputSizeHeight.value),
                    borderRadius: Number.parseFloat(this.inputSizeCornerRadius.value)
                }
            }
        }
    }

    private uploadFile(files: FileList | undefined | null) {
        if (files == undefined) {
            // todo
            throw new Error("file not loaded")
        }

        let file: File = files[0]
        // todo rename file for product parameters
        let formData = new FormData()
        formData.append('file', file)
        Api.uploadFile(formData)
            .then((data) => {
                let preview: File = <File>data.get('file')
                let filePath: string = <string>data.get('filePath')
            })
    }

    addToCart(): void {
    }


}