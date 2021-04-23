"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
const getInitialParams = 'http://62.244.50.147:8080/api/stickers/getInitialParams?type=SELF_ADHESIVE';
class OrderServiceApi {
    static restGetRequest() {
        return __awaiter(this, void 0, void 0, function* () {
            const response = yield fetch(this.serviceUrl.toString());
            response.jsonBody = yield response.json();
            return response;
        });
    }
    static restPostRequest(request) {
        return __awaiter(this, void 0, void 0, function* () {
            const response = yield fetch(request);
            response.jsonBody = yield response.json();
            return response;
        });
    }
}
OrderServiceApi.serviceUrl = new URL(getInitialParams);
OrderServiceApi.restGetRequest()
    .then(response => {
    if (response.status != 200) {
        throw new Error(`Server error: ${response.status} ${response.statusText} `);
    }
    if (response.jsonBody) {
        renderOptions(response.jsonBody);
    }
    else {
        throw new Error("Empty jsonBody");
    }
})
    .catch(error => console.log(error));
function renderOptions(response) {
    console.log(response);
    const materialType = response.materialType;
    const cuttingType = response.cuttingType;
    let materialTypeSelect = document.getElementById('sticker_material');
    let cuttingTypeMapSelect = document.getElementById('sticker_cutting_type');
    if (cuttingTypeMapSelect == null || materialTypeSelect == null)
        return;
    Array()
        .sort((a, b) => a.index - b.index)
        .forEach(function (value, index) {
        let option = document.createElement('option');
        option.innerText = value.name;
        option.value = value.type;
        materialTypeSelect.appendChild(option);
    });
    // new Map(Object.entries(materialType)).forEach(function (value, key) {
    //     let option = document.createElement('option')
    //     option.innerText = value
    //     option.value = key
    //     materialTypeSelect.appendChild(option)
    // });
    new Map(Object.entries(cuttingType)).forEach(function (value, key) {
        let option = document.createElement('option');
        option.innerText = value;
        option.value = key;
        cuttingTypeMapSelect.appendChild(option);
    });
}
class OrderForm {
    constructor() {
        this.inputSize = document.getElementById("sticker_size_input");
        this.inputSize.addEventListener('input', (e) => {
            this.outputSize.innerHTML = this.inputSize.value;
            this.createProduct();
        });
        this.inputCount = document.getElementById("sticker_quantity_input");
        this.inputCount.addEventListener('input', () => {
            this.outputCount.innerHTML = this.inputCount.value;
            this.createProduct();
        });
        this.inputMaterialType = document.getElementById("sticker_material");
        this.inputMaterialType.addEventListener("change", () => this.createProduct());
        this.inputCutType = document.getElementById("sticker_cutting_type");
        this.inputCutType.addEventListener("change", () => this.createProduct());
        this.outputSize = document.getElementById("sticker_size_value");
        this.outputSize.innerHTML = this.inputSize.value;
        this.outputSizePreview = document.getElementById("sticker_preview_size_label");
        this.outputCount = document.getElementById("sticker_quantity_value");
        this.outputPrice = document.getElementById("sticker_result_price");
        this.outputDate = document.getElementById("sticker_result_date");
    }
    createProduct() {
        this.roundSticker = {
            productType: ProductType.STICKER,
            quantity: Number.parseFloat(this.inputCount.value),
            materialType: MaterialType[this.inputMaterialType.value],
            cuttingType: CuttingType[this.inputCutType.value],
            size: {
                diameter: Number.parseFloat(this.inputSize.value),
            }
        };
        // console.log(this.roundSticker)
        this.calculateProduct();
    }
    calculateProduct() {
        let request = new Request("http://62.244.50.147:8080/api/calc", {
            method: "POST",
            body: JSON.stringify(this.roundSticker),
            headers: {
                "Content-Type": "application/json"
            }
        });
        OrderServiceApi.restPostRequest(request)
            .then((response) => {
            if (response.status != 200) {
                throw new Error(`Server error: ${response.status} ${response.statusText} `);
            }
            if (response.jsonBody) {
                this.updateOrderForm(response.jsonBody);
            }
            else {
                throw new Error("Empty jsonBody");
            }
        })
            .catch((err) => console.log(err.toString()));
    }
    updateOrderForm(response) {
        console.log(response);
        if (response.size && response.size.diameter && response.quantity && response.totalPrice) {
            this.outputSize.innerHTML = response.size.diameter.toString();
            this.outputSizePreview.innerHTML = response.size.diameter.toString();
            this.outputCount.innerHTML = response.quantity.toString();
            this.outputPrice.innerHTML = response.totalPrice.toString();
            this.outputDate.innerHTML = this.getReadyDate(response);
        }
    }
    getReadyDate(response) {
        let date = new Date();
        let options = {
            month: 'long',
            day: 'numeric',
            weekday: 'long',
            timezone: 'UTC',
            hour: 'numeric',
        };
        /**
         *    'Паперова наліпка', 0
         * 'Паперова наліпка з ламінацією', 1
         'PET наліпка', 2
         'Вінілова наліпка з ламінацією', 3
         'Прозора наліпка (без білого кольору)', 4
         */
        switch (date.getDay()) {
            case 1: // Sunday
                if (response.materialType != MaterialType.RITRAMA_LAMINATED) {
                    date.setDate(date.getDate() + 1);
                }
                else if (response.materialType == MaterialType.RITRAMA_LAMINATED) {
                    date.setDate(date.getDate() + 2);
                }
                break;
            case 6: // Saturday
                if (response.materialType != MaterialType.RITRAMA_LAMINATED) {
                    date.setDate(date.getDate() + 2);
                }
                else if (response.materialType == MaterialType.RITRAMA_LAMINATED) {
                    date.setDate(date.getDate() + 3);
                }
                break;
            case 5: //Friday
                if (date.getHours() > 12) {
                    if (response.materialType != MaterialType.RITRAMA_LAMINATED) {
                        date.setDate(date.getDate() + 3);
                    }
                    else if (response.materialType == MaterialType.RITRAMA_LAMINATED) {
                        date.setDate(date.getDate() + 4);
                    }
                }
                else {
                    if (response.materialType != MaterialType.RITRAMA_LAMINATED) {
                        date.setDate(date.getDate());
                    }
                    else if (response.materialType == MaterialType.RITRAMA_LAMINATED) {
                        date.setDate(date.getDate() + 3);
                    }
                }
                break;
            default: // monday - thursday
                if (date.getHours() > 12) {
                    if (response.materialType != MaterialType.RITRAMA_LAMINATED) {
                        date.setDate(date.getDate() + 1);
                    }
                    else if (response.materialType == MaterialType.RITRAMA_LAMINATED) {
                        date.setDate(date.getDate() + 2);
                    }
                }
                else {
                    if (response.materialType != MaterialType.RITRAMA_LAMINATED) {
                        date.setDate(date.getDate());
                    }
                    else if (response.materialType == MaterialType.RITRAMA_LAMINATED) {
                        date.setDate(date.getDate() + 1);
                    }
                }
        }
        date.setHours(18);
        return date.toDateString();
    }
    addToCart() {
        // todo
    }
}
new OrderForm();
