import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { VideoSearch } from "../types/external/video-search";
import { videoList } from "../../mock/videos-minecraft-mock";
import { Video } from "../types/external/video";
import { video } from "../../mock/video-minecraft";
import { Idea } from "../types/internal/idea";
import { LoginDTO } from "../types/dto/login-dto";

@Injectable({
    providedIn:'root',
})

export class ApiBack {
    private urlBase = "http://localhost:8080";
    private client = inject(HttpClient);

    

    getVideosBySearch(search:string):Observable<VideoSearch[]>{
        let params = new HttpParams();
        params = params.append("search", search);
        return this.client.get<VideoSearch[]>(this.urlBase + "/yt", {params:params});
    }

    getMockData():VideoSearch[]{
        return videoList;
    }
    getMockDataData():Video{
        return video;
    }

    getVideoById(id:string):Observable<Video>{
        return this.client.get<Video>(this.urlBase+"/yt/video/"+id);
    }

    saveNewIdea(newIdea:Idea):Observable<Idea>{
        let headers = new HttpHeaders();
        headers = headers.set('Authorization', "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJjb3BpYS12aWRlby1hcHAiLCJleHAiOjE3NzAzNDgwNzIsInN1YiI6InRlc3RlQC5jb20ifQ.r5Ymt_BmcLI723GdJQMgF418u8WS74w9v7_0QqEU9SY");

        return this.client.post<Idea>(this.urlBase + "/ideas", newIdea, {headers});
    }


    login(user:LoginDTO):Observable<string>{
        return this.client.post(this.urlBase + "/auth/login", user, {responseType:'text'});
    }




}