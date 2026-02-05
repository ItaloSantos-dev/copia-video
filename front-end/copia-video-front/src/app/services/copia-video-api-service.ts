import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { VideoSearch } from '../types/video-search';

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
}
