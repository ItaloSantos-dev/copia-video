import { Component, inject, signal } from '@angular/core';
import { CopiaVideoApiService } from '../../services/copia-video-api-service';
import { VideoSearch } from '../../types/external/video-search';
import { isEmpty } from 'rxjs';
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-home',
  imports: [RouterLink],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  apiCopiaVideoService = inject(CopiaVideoApiService);

  search = signal("");
  videos = signal(<VideoSearch[]>([]));

  
  getMockData(){
    this.videos.set(this.apiCopiaVideoService.getMockData())
  }


  getVideosBySearh(_search:string){
    if (_search.length==0) {
      window.alert("Envie um valor válido")
    }
    else{
      this.search.set(_search)
      this.apiCopiaVideoService.getVideosBySearch(_search).subscribe({
      next:(dados)=>{
        console.log(dados[0].snippet.thumbnails.default);
        
        this.videos.set(dados);
      },
      error:(erro)=>{
        this.search.set("Erro ao acessar videos");
      }
    })
    }
  }


}
