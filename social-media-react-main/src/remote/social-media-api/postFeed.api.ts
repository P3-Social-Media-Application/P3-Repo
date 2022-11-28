import Post from "../../models/Post";
import socialClient, { socialApiResponse } from "./socialClient";

const baseURL = "/post"

export const apiGetAllPosts = async (): Promise<socialApiResponse> => {
    const response = await socialClient.get<any>(
        `${baseURL}`
    );
    console.log(response.data)
    return { status: response.status, payload: response.data };
}

export const apiUpsertPost = async (post: Post): Promise<socialApiResponse> => {
    const response = await socialClient.put<any>(
        `${baseURL}`,
        post
    );
    return { status: response.status, payload: response.data };
}

