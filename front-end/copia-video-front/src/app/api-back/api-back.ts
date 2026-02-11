import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { VideoSearch } from "../types/external/video-search";
import { videoList } from "../../mock/videos-minecraft-mock";
import { Video } from "../types/external/video";
import { video } from "../../mock/video-minecraft";
import { Idea } from "../types/internal/idea";
import { LoginDTO } from "../types/dto/login-dto";
import { RegisterDTO } from "../types/dto/register-dto";
import { User } from "../types/internal/user";

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
        return this.client.post<Idea>(this.urlBase + "/ideas", newIdea);
    }


    login(user:LoginDTO):Observable<string>{
        return this.client.post(this.urlBase + "/auth/login", user, {responseType:'text'});
    }

    register(newUser:RegisterDTO):Observable<User>{
        return this.client.post<User>(this.urlBase + "/auth/register", newUser);
    }

    getMyIdeas(){
        return this.client.get<Idea[]>(this.urlBase + "/ideas");
    }

    deleteIdeaById(id:string):Observable<any>{
        return this.client.delete(this.urlBase + "/ideas/" + id);
    }

    getIdeaById(id:string){
        return this.client.get<Idea>(this.urlBase + "/ideas/" + id);
    }

    updateIdeaById(id:string, idea:Idea):Observable<Idea>{
        return this.client.put<Idea>(this.urlBase + "/ideas/" + id, idea);
    }

    getUsersCount():Observable<number>{
        return this.client.get<number>(this.urlBase + "/users/count")
    }

}