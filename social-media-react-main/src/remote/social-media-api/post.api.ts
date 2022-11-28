import socialClient, { socialApiResponse } from "./socialClient";

const baseURL = "/post";

export const apiGetPosts = async (): Promise<socialApiResponse> => {
    const response = await socialClient.get<any>(
        baseURL
    );
    return { status: response.status, payload: response.data }
}

export const apiUpsertPost = async (post: any): Promise<socialApiResponse> => {
    const response = await socialClient.put<any>(baseURL, post, {withCredentials: true});
    return { status: response.status, payload: response.data };
}