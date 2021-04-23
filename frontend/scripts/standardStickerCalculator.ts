const getInitialParams = 'http://62.244.50.147:8080/api/stickers/getInitialParams?type=round'

interface ApiResponse<T> extends Response {
    jsonBody?: T;
}

interface CalculationParams {
    materialType: MaterialType
    cuttingType: CuttingType
}

class OrderServiceApi {
    public static serviceUrl: URL = new URL(getInitialParams);

    public static async restGetRequest<T>(): Promise<ApiResponse<T>> {
        const response: ApiResponse<T> = await fetch(this.serviceUrl.toString());
        response.jsonBody = await response.json()
        return response;
    }

    public static async restPostRequest<T>(request: Request): Promise<ApiResponse<T>> {
        const response: ApiResponse<T> = await fetch(request);
        response.jsonBody = await response.json()
        return response;
    }
}

OrderServiceApi.restGetRequest<CalculationParams>()
    .then(response => {
        if (response.status != 200) {
            throw new Error(`Server error: ${response.status} ${response.statusText} `)
        }
        if (response.jsonBody) {
            renderOptions(response.jsonBody)
        } else {
            throw new Error("Empty jsonBody")
        }
    })
    .catch(error => console.log(error))

function renderOptions(response: CalculationParams): void {
    const materialType: MaterialType = response.materialType
    const cuttingType: CuttingType = response.cuttingType

    let materialTypeSelect = <HTMLSelectElement>document.getElementById('sticker_material')
    let cuttingTypeMapSelect = <HTMLSelectElement>document.getElementById('sticker_cutting_type')
    if (cuttingTypeMapSelect == null || materialTypeSelect == null) return
    Array<Material>()
        .sort((a: Material, b: Material) => a.index - b.index)
        .forEach(function (value, index) {
            let option = document.createElement('option')
            option.innerText = value.name;
            option.value = value.type
            materialTypeSelect.appendChild(option)
        });

    new Map(Object.entries(materialType)).forEach(function (value, key) {
        let option = document.createElement('option')
        option.innerText = value
        option.value = key
        materialTypeSelect.appendChild(option)
    });

    new Map(Object.entries(cuttingType)).forEach(function (value, key) {
        let option = document.createElement('option')
        option.innerText = value
        option.value = key
        cuttingTypeMapSelect.appendChild(option)
    });
}


class OrderForm {
    //inputs
    inputSize: HTMLInputElement
    inputCount: HTMLInputElement
    inputMaterialType: HTMLInputElement
    inputCutType: HTMLInputElement
    //outputs
    outputSize: HTMLElement
    outputSizePreview: HTMLElement
    outputCount: HTMLElement
    outputPrice: HTMLElement
    outputDate: HTMLElement

    roundSticker?: PrintingProduct

    constructor() {
        this.inputSize = <HTMLInputElement>document.getElementById("sticker_size_input")
        this.inputSize.addEventListener('input', (e) => {
            this.outputSize.innerHTML = this.inputSize.value
            this.createProduct()
        })

        this.inputCount = <HTMLInputElement>document.getElementById("sticker_quantity_input")
        this.inputCount.addEventListener('input', () => {
            this.outputCount.innerHTML = this.inputCount.value
            this.createProduct()
        })

        this.inputMaterialType = <HTMLInputElement>document.getElementById("sticker_material")
        this.inputMaterialType.addEventListener("change", () => this.createProduct())

        this.inputCutType = <HTMLInputElement>document.getElementById("sticker_cutting_type")
        this.inputCutType.addEventListener("change", () => this.createProduct())

        this.outputSize = <HTMLElement>document.getElementById("sticker_size_value")
        this.outputSize.innerHTML = this.inputSize.value;
        this.outputSizePreview = <HTMLElement>document.getElementById("sticker_preview_size_label")
        this.outputCount = <HTMLElement>document.getElementById("sticker_quantity_value")
        this.outputPrice = <HTMLElement>document.getElementById("sticker_result_price")
        this.outputDate = <HTMLElement>document.getElementById("sticker_result_date")
    }

    public createProduct() {
        this.roundSticker = {
            quantity: Number.parseFloat(this.inputCount.value),
            materialType: MaterialType[this.inputMaterialType.value as keyof typeof MaterialType], //MaterialType.RAFLATAC,
            cuttingType: CuttingType[this.inputCutType.value as keyof typeof CuttingType],
            size: {
                diameter: Number.parseFloat(this.inputSize.value),
            }
        };

        // console.log(this.roundSticker)
        this.calculateProduct()
    }

    public calculateProduct() {
        let request: Request = new Request(
            "http://62.244.50.147:8080/api/calc",
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
                    this.updateOrderForm(response.jsonBody)
                } else {
                    throw new Error("Empty jsonBody")
                }
            })
            .catch((err) => console.log(err.toString()))
    }

    public updateOrderForm(response: PrintingProduct) {
        console.log(response)

        if (response.size && response.size.diameter && response.quantity && response.totalPrice) {
            this.outputSize.innerHTML = response.size.diameter.toString();
            this.outputSizePreview.innerHTML = response.size.diameter.toString()

            this.outputCount.innerHTML = response.quantity.toString()
            this.outputPrice.innerHTML = response.totalPrice.toString()
            this.outputDate.innerHTML = this.getReadyDate(response)
        }
    }

    public getReadyDate(response: PrintingProduct): string{
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
                } else if (response.materialType == MaterialType.RITRAMA_LAMINATED) {
                    date.setDate(date.getDate() + 2);
                }
                break;
            case 6: // Saturday
                if (response.materialType != MaterialType.RITRAMA_LAMINATED) {
                    date.setDate(date.getDate() + 2);
                } else if (response.materialType == MaterialType.RITRAMA_LAMINATED) {
                    date.setDate(date.getDate() + 3);
                }
                break;
            case 5: //Friday
                if (date.getHours() > 12) {
                    if (response.materialType != MaterialType.RITRAMA_LAMINATED) {
                        date.setDate(date.getDate() + 3);
                    } else if (response.materialType == MaterialType.RITRAMA_LAMINATED) {
                        date.setDate(date.getDate() + 4);
                    }
                } else {
                    if (response.materialType != MaterialType.RITRAMA_LAMINATED) {
                        date.setDate(date.getDate());
                    } else if (response.materialType == MaterialType.RITRAMA_LAMINATED) {
                        date.setDate(date.getDate() + 3);
                    }
                }
                break;
            default: // monday - thursday
                if (date.getHours() > 12) {
                    if (response.materialType != MaterialType.RITRAMA_LAMINATED) {
                        date.setDate(date.getDate() + 1);
                    } else if (response.materialType == MaterialType.RITRAMA_LAMINATED) {
                        date.setDate(date.getDate() + 2);
                    }
                } else {
                    if (response.materialType != MaterialType.RITRAMA_LAMINATED) {
                        date.setDate(date.getDate());
                    } else if (response.materialType == MaterialType.RITRAMA_LAMINATED) {
                        date.setDate(date.getDate() + 1);
                    }
                }
        }
        date.setHours(18);

        return date.toDateString()
    }

    public addToCart() {
        // todo
    }

}

new OrderForm()

// const getEnumType<T>(keyValue: string) {
//     return T[keyValue as keyof typeof T]
// }
