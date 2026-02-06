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

    saveNewIdea(newIdea:Idea, token:string):Observable<Idea>{
        let headers = new HttpHeaders();
        headers = headers.set('Authorization', token);

        return this.client.post<Idea>(this.urlBase + "/ideas", newIdea, {headers});
    }


    login(user:LoginDTO):Observable<string>{
        return this.client.post(this.urlBase + "/auth/login", user, {responseType:'text'});
    }




}