import {ApiConfig} from "./ApiConfig.js";
import {ApiResponse} from "./ApiResponse.js";
import {MaterialGroupType} from "../types/MaterialGroupType.js";
import {PrintingProduct} from "../types/PrintingProduct.js";
import {CalculationParams} from "../types/CalculationParams.js";

export interface IOrderService {
    calculateProduct(printingProduct: PrintingProduct): Promise<PrintingProduct>

    getInitParams(materialGroupType: MaterialGroupType): CalculationParams
}

export class ApiBase {
    public static serviceUrl: URL = new URL(ApiConfig.INIT_PARAMS_URL);

    public static createPostRequest(endpoint: string, value: any): Request {
        return new Request(
            `${ApiConfig.URL}${endpoint}`,
            {
                method: "POST",
                body: JSON.stringify(value),
                headers: {
                    "Content-Type": "application/json"
                }
            }
        )
    }

    public static async restGetRequest<T>(materialGroupType: MaterialGroupType): Promise<ApiResponse<T>> {
        let url: URL = new URL(`${ApiConfig.initParamsByType}${materialGroupType}`)
        const response: ApiResponse<T> = await fetch(url.toString());
        response.jsonBody = await response.json()
        return response;
    }

    public static async restPostRequest<T>(request: Request): Promise<ApiResponse<T>> {
        const response: ApiResponse<T> = await fetch(request);
        response.jsonBody = await response.json()
        return response;
    }
}

export class Api implements IOrderService {
    public async calculateProduct(printingProduct: PrintingProduct): Promise<PrintingProduct> {
        return this.WrapApiCall<PrintingProduct>(ApiBase.restPostRequest<PrintingProduct>(ApiBase.createPostRequest("calc", printingProduct.productType)))
    }

    public async getInitParams(materialGroupType: MaterialGroupType): CalculationParams {
        return undefined;
    }

    public async WrapApiCall<T>(call: () => Promise<ApiResponse<T>>): Promise<T> {
        try {
            const response: ApiResponse<T> = await call()
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