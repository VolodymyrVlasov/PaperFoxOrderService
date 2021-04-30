import {ApiConfig} from "./ApiConfig.js";
import {ApiResponse} from "./ApiResponse.js";

export class OrderServiceApi {
    public static serviceUrl: URL = new URL(ApiConfig.INIT_PARAMS_URL);

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