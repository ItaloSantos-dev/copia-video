import { inject, Injectable } from '@angular/core';
import { ApiBack } from '../../api-back/api-back';
import { VideoSearch } from '../../types/external/video-search';
import { Video } from '../../types/external/video';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class VideoService {
  apiBack = inject(ApiBack)


  getVideosBySearch(search:string):Observable<VideoSearch[]>{
    return this.apiBack.getVideosBySearch(search).pipe();
  }

  getVideoById(id:string):Observable<Video>{
    return this.apiBack.getVideoById(id).pipe();
  }

  getMockData():VideoSearch[]{
      return this.apiBack.getMockData();
  }


}
