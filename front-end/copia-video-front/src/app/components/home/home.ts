import { Component, inject, signal } from '@angular/core';
import { VideoSearch } from '../../types/external/video-search';
import { isEmpty } from 'rxjs';
import { RouterLink } from "@angular/router";
import { VideoService } from '../../services/videoService/video-service';
import { Video } from '../../types/external/video';

@Component({
  selector: 'app-home',
  imports: [RouterLink],
  templateUrl: './home.html',
  styleUrl: './home.css',
})

export class Home {
  videoService = inject(VideoService);

  search = signal("");
  videos = signal(<VideoSearch[]>([]));

  
  getMockData(){
    this.videos.set(this.videoService.getMockData())
  }


  getVideosBySearh(_search:string){
    if (_search.length==0) {
      window.alert("Envie um valor válido")
    }
    else{
      this.search.set(_search)
      this.videoService.getVideosBySearch(_search).subscribe({
        next:(dados)=>{
          this.videos.set(dados);
        },
        error:(erro)=>{
          console.log(erro);
        }
      })
      
    }
  }

}
