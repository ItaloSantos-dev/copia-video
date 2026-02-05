import { Thumbnails } from "./thumbnails"

export interface Snippet{
    channelId:string,
    channelTitle:string,
    thumbnails:Thumbnails,
    title:string,
    publishTime:string
}