// import {
//     CalculationParams,
//     CuttingType,
//     Material,
//     MaterialGroupType,
//     MaterialType,
//     PrintingProduct,
//     ProductType
// } from "../../types/index.js";
// import {ApiConfig} from "../../api/ApiConfig.js";
// import {OrderServiceApi} from "../../api/PaperFoxApi.js";
//
// export class CalculatorContainer {
//     //inputs
//     inputSize: HTMLInputElement
//     inputCount: HTMLInputElement
//     inputMaterialType: HTMLInputElement
//     inputCutType: HTMLInputElement
//     //outputs
//     outputSize: HTMLElement
//     outputSizePreview: HTMLElement
//     outputCount: HTMLElement
//     outputPrice: HTMLElement
//     outputDate: HTMLElement
//     roundSticker?: PrintingProduct
//
//     constructor() {
//         this.inputSize = <HTMLInputElement>document.getElementById("sticker_size_input")
//         this.outputSizePreview = <HTMLElement>document.getElementById("sticker_preview_size_label")
//         this.inputCount = <HTMLInputElement>document.getElementById("sticker_quantity_input")
//         this.inputMaterialType = <HTMLInputElement>document.getElementById("sticker_material")
//         this.inputCutType = <HTMLInputElement>document.getElementById("sticker_cutting_type")
//
//         this.outputSize = <HTMLElement>document.getElementById("sticker_size_value")
//         this.outputSize.innerHTML = this.inputSize.value;
//         this.outputCount = <HTMLElement>document.getElementById("sticker_quantity_value")
//         this.outputPrice = <HTMLElement>document.getElementById("sticker_result_price")
//         this.outputDate = <HTMLElement>document.getElementById("sticker_result_date")
//
//         this.inputCutType.addEventListener("change", () => this.createProduct())
//         this.inputMaterialType.addEventListener("change", () => this.createProduct())
//         this.inputSize.addEventListener('input', (e) => {
//             this.outputSize.innerHTML = this.inputSize.value
//             this.createProduct()
//         })
//         this.inputCount.addEventListener('input', () => {
//             this.outputCount.innerHTML = this.inputCount.value
//             this.outputSizePreview.innerHTML = this.inputSize.value
//             this.createProduct()
//         })
//     }
//
//     public init() {
//         let calculatorContainer: CalculatorContainer;
//
//         OrderServiceApi.restGetRequest<CalculationParams>(MaterialGroupType.SELF_ADHESIVE)
//             .then(response => {
//                 if (response.status != 200) {
//                     throw new Error(`Server error: ${response.status} ${response.statusText} `)
//                 }
//                 if (response.jsonBody) {
//                     CalculatorContainer.renderOptions(response.jsonBody)
//                 } else {
//                     throw new Error("Empty jsonBody")
//                 }
//             })
//             .then(() => calculatorContainer = new CalculatorContainer())
//             .then(() => calculatorContainer.createProduct())
//             .catch(error => console.log(error))
//     }
//
//     public createProduct() {
//         this.roundSticker = {
//             productType: ProductType.STICKER,
//             quantity: Number.parseFloat(this.inputCount.value),
//             material: {
//                 materialType: MaterialType[this.inputMaterialType.value as keyof typeof MaterialType]
//             },
//             cuttingType: CuttingType[this.inputCutType.value as keyof typeof CuttingType],
//             size: {
//                 diameter: Number.parseFloat(this.inputSize.value),
//             }
//         };
//
//         this.calculateProduct()
//     }
//
//     public calculateProduct() {
//         let request: Request = new Request(
//             `${ApiConfig.URL}calc`,
//             {
//                 method: "POST",
//                 body: JSON.stringify(this.roundSticker),
//                 headers: {
//                     "Content-Type": "application/json"
//                 }
//             }
//         )
//
//         OrderServiceApi.restPostRequest<PrintingProduct>(request)
//             .then((response) => {
//                 if (response.status != 200) {
//                     throw new Error(`Server error: ${response.status} ${response.statusText} `)
//                 }
//                 if (response.jsonBody) {
//                     // console.log(response.jsonBody)
//                     this.updateOrderForm(response.jsonBody)
//                 } else {
//                     throw new Error("Empty jsonBody")
//                 }
//             })
//             .catch((err) => console.log(err.toString()))
//     }
//
//     public updateOrderForm(response: PrintingProduct) {
//         if (response.size && response.size.diameter && response.quantity && response.totalPrice) {
//             if (response.productionTime && response.quantityPerSheet) {
//                 this.outputSizePreview.innerHTML = response.size.diameter.toString()
//                 this.inputCount.step = response.quantityPerSheet.toString()
//                 this.outputCount.innerHTML = response.quantity.toString() // fixme
//                 this.outputPrice.innerHTML = response.totalPrice.toString()
//
//                 let options: Intl.DateTimeFormatOptions = {
//                     day: "numeric", month: "long",
//                     hour: "2-digit", minute: "2-digit"
//                 };
//
//                 let date =  new Date(Date.parse(response.productionTime.toString()))
//                 console.log(date)
//                 console.log(date.toLocaleDateString("uk-UA", options) );
//                 this.outputDate.innerHTML = date.toLocaleDateString("uk-UA", options)
//             }
//         }
//     }
//
//     public static renderOptions(response: CalculationParams): void {
//         const materialType: Material[] = response.materialType
//         const cuttingType: CuttingType = response.cuttingType
//
//         let materialTypeSelect = <HTMLSelectElement>document.getElementById('sticker_material')
//         let cuttingTypeMapSelect = <HTMLSelectElement>document.getElementById('sticker_cutting_type')
//         if (cuttingTypeMapSelect == null || materialTypeSelect == null) return
//
//         materialType.forEach((material) => {
//             let option = document.createElement('option')
//             if (material.name)
//                 option.innerText = material.name
//             option.value = material.materialType
//             materialTypeSelect.appendChild(option)
//         })
//
//         new Map(Object.entries(cuttingType)).forEach(function (value, key) {
//             let option = document.createElement('option')
//             option.innerText = value
//             option.value = key
//             cuttingTypeMapSelect.appendChild(option)
//         });
//     }
//
//     public addToCart() {
//         // todo
//     }
// }