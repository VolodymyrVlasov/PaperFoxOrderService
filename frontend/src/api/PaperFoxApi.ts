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
        let url: URL = new URL(ApiConfig.URL + endpoint);
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
        return PaperFoxApi.WrapApiCall<PrintingProduct>("calc", undefined, printingProduct.productType);
    }

    public async getInitParams(materialGroupType: MaterialGroupType): Promise<CalculationParams> {
        return PaperFoxApi.WrapApiCall<CalculationParams>("getRenderParams", new URLSearchParams(`type=${materialGroupType}`));
    }

    private static async WrapApiCall<T>(
        endpoint: string,
        params?: URLSearchParams,
        value?: any,
        method = "GET"
    ): Promise<T> {
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
}

export const Api = new PaperFoxApi()

