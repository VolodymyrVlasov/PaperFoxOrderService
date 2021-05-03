import {ApiConfig} from "./ApiConfig.js";
import {ApiResponse} from "./ApiResponse.js";
import {MaterialGroupType} from "../types/MaterialGroupType.js";
import {PrintingProduct} from "../types/PrintingProduct.js";
import {CalculationParams} from "../types/CalculationParams.js";

export interface IPaperFoxApi {
    calculateProduct(printingProduct: PrintingProduct): Promise<PrintingProduct>
    getInitParams(materialGroupType: MaterialGroupType): Promise<CalculationParams>
}

export class PaperFoxApi implements IPaperFoxApi {
    private static newRequest(
        endpoint: string,
        params?: URLSearchParams,
        value?: any,
        method="GET"
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
        method="GET"
    ): Promise<T> {
        let req: Request = PaperFoxApi.newRequest(endpoint, params, value, method);

        try {
            const response: ApiResponse<T> = await fetch(req);
            if (response.status != 200) {
                throw new Error(`Server error: ${response.status} ${response.statusText} `)
            }
            if (response.jsonBody) {
                return await response.json()
            } else {
                throw new Error("Empty jsonBody")
            }
        } catch(error) {
           return error
        }
    }
}