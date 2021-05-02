import {ApiConfig} from "./ApiConfig.js";
import {ApiResponse} from "./ApiResponse.js";
import {MaterialGroupType} from "../types/MaterialGroupType";

export class OrderServiceApi {
    public static serviceUrl: URL = new URL(ApiConfig.INIT_PARAMS_URL);

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