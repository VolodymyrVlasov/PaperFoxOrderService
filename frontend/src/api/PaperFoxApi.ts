import {ApiConfig} from "./ApiConfig.js";
import {ApiResponse} from "./ApiResponse.js";
import {MaterialGroupType} from "../types/MaterialGroupType.js";
import {PrintingProduct} from "../types/PrintingProduct.js";
import {CalculationParams} from "../types/CalculationParams.js";
import {IPaperFoxApi} from "./IPaperFoxApi.js";

class PaperFoxApi implements IPaperFoxApi {
    private static newRequest(
        endpoint: string,
        params?: URLSearchParams,
        value?: any,
        method = "GET"
    ): Request {
        let url: URL = new URL(endpoint);
        params?.forEach(((value, key) => {
            url.searchParams.append(key, value);
        }))

        return new Request(
            url.toString(),
            {
                method: method,
                body: JSON.stringify(value),
                headers: {
                    "Content-Type": "application/json"
                }
            }
        )
    }

    public async calculateProduct(printingProduct: PrintingProduct): Promise<PrintingProduct> {
        return PaperFoxApi.WrapApiCall<PrintingProduct>(ApiConfig.URL + "calc", undefined, printingProduct, "POST");
    }

    public async getInitParams(materialGroupType: MaterialGroupType): Promise<CalculationParams> {
        return PaperFoxApi.WrapApiCall<CalculationParams>(ApiConfig.URL + "getRenderParams", new URLSearchParams(`type=${materialGroupType}`));
    }

    public async uploadFile(formData: FormData): Promise<FormData> {
        return PaperFoxApi.WrapApiFile(ApiConfig.FILE_SERVICE + "file/upload", formData)
    }

    private static async WrapApiCall<T>(endpoint: string, params?: URLSearchParams, value?: any, method = "GET"): Promise<T> {
        let req: Request = PaperFoxApi.newRequest(endpoint, params, value, method);

        try {
            const response: ApiResponse<T> = await fetch(req);
            // console.log(response.jsonBody)
            if (response.status != 200) {
                throw new Error(`Server error: ${response.status} ${response.statusText} `)
            }
            response.jsonBody = await response.json()
            if (response.jsonBody) {
                return response.jsonBody
            } else {
                throw new Error("Empty jsonBody")
            }
        } catch (error) {
            throw new Error(error)
        }
    }

    private static async WrapApiFile(endpoint: string, value?: FormData): Promise<FormData> {
        let req: Request = new Request(
            endpoint.toString(),
            {
                method: 'POST',
                body: value
            }
        )
        try {
            const response: Response = await fetch(req);
            // console.log(response.jsonBody)
            if (response.status != 200) {
                throw new Error(`Server error: ${response.status} ${response.statusText} `)
            }
            return await response.formData()
        } catch (error) {
            throw new Error(error)
        }
    }


    //http://62.244.50.147:9000/files/13272c07-3a4a-4bc0-8a43-021bf0d1cd73_cupo.jpg


}

export const Api = new PaperFoxApi()

