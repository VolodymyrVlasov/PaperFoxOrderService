
const getInitialParams = 'http://62.244.50.147:8080/api/stickers/getInitialParams?type=SELF_ADHESIVE'

interface ApiResponse<T> extends Response {
    jsonBody?: T;
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

    const materialType: Material[] = response.materialType
    const cuttingType: CuttingType = response.cuttingType

    let materialTypeSelect = <HTMLSelectElement>document.getElementById('sticker_material')
    let cuttingTypeMapSelect = <HTMLSelectElement>document.getElementById('sticker_cutting_type')
    if (cuttingTypeMapSelect == null || materialTypeSelect == null) return

    materialType.forEach((material) => {
        let option = document.createElement('option')
        option.innerText = material.name
        option.value = material.index.toString()
        materialTypeSelect.appendChild(option)
    })


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
        this.outputSizePreview = <HTMLElement>document.getElementById("sticker_preview_size_label")

        this.inputCount = <HTMLInputElement>document.getElementById("sticker_quantity_input")
        this.inputCount.addEventListener('input', () => {
            this.outputCount.innerHTML = this.inputCount.value
            this.outputSizePreview.innerHTML = this.inputCount.value
            this.createProduct()
        })

        this.inputMaterialType = <HTMLInputElement>document.getElementById("sticker_material")
        this.inputMaterialType.addEventListener("change", () => this.createProduct())

        this.inputCutType = <HTMLInputElement>document.getElementById("sticker_cutting_type")
        this.inputCutType.addEventListener("change", () => this.createProduct())

        this.outputSize = <HTMLElement>document.getElementById("sticker_size_value")
        this.outputSize.innerHTML = this.inputSize.value;

        this.outputCount = <HTMLElement>document.getElementById("sticker_quantity_value")
        this.outputPrice = <HTMLElement>document.getElementById("sticker_result_price")
        this.outputDate = <HTMLElement>document.getElementById("sticker_result_date")
    }

    public createProduct() {
        this.roundSticker = {
            productType: ProductType.STICKER,
            quantity: Number.parseFloat(this.inputCount.value),
            material: {
                // name: this.inputMaterialType.textContent,
                index: Number(this.inputMaterialType.value),
            },
            cuttingType: CuttingType[this.inputCutType.value as keyof typeof CuttingType],
            size: {
                diameter: Number.parseFloat(this.inputSize.value),
            }
        };

        console.log(this.roundSticker)
        this.calculateProduct()
    }

    public calculateProduct() {
        console.log(this.roundSticker)
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

        if (response.size && response.size.diameter && response.quantity && response.totalPrice && response.productionTime) {
            this.outputSize.innerHTML = response.size.diameter.toString();
            this.outputSizePreview.innerHTML = response.size.diameter.toString()

            this.outputCount.innerHTML = response.quantity.toString()
            this.outputPrice.innerHTML = response.totalPrice.toString()
            this.outputDate.innerHTML = response.productionTime.toString()
        }
    }

    public addToCart() {
        // todo
    }
}

new OrderForm()
