import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { VideoSearch } from '../types/external/video-search';
import { videoList } from '../../mock/videos-minecraft-mock';
import { Video } from '../types/external/video';
import { Idea } from '../types/internal/idea';

@Injectable({
  providedIn: 'root',
})
export class CopiaVideoApiService {
  private urlBase = "http://localhost:8080";
  private client:HttpClient;

  constructor(private _client:HttpClient){
    this.client = _client;
  }

  getVideosBySearch(search:string):Observable<VideoSearch[]>{
    let params = new HttpParams();
    params = params.append("search", search);
    return this.client.get<VideoSearch[]>(this.urlBase + "/yt", {params:params});
  }

  getMockData():VideoSearch[]{
    return videoList;
  }

  getVideoById(id:string):Observable<Video>{
    console.log(this.urlBase+"/yt/video/"+id);
    
    return this._client.get<Video>(this.urlBase+"/yt/video/"+id);
  }

  saveNewIdea(newIdea:Idea):Observable<Idea>{
    let headers = new HttpHeaders();

    //token vai ser salvo no storage quando fizer login
    headers = headers.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJjb3BpYS12aWRlby1hcHAiLCJleHAiOjE3NzAzMzYwNjIsInN1YiI6InRlc3RlQC5jb20ifQ.nXuYCluPv4LJG_P6Fv2xqJWQxNkJygC-kV1BzCPO2T4");

    return this._client.post<Idea>(this.urlBase + "/ideas", newIdea, {headers})
  }

}
