import { Component, inject, signal } from '@angular/core';
import { VideoSearch } from '../../types/external/video-search';
import { isEmpty } from 'rxjs';
import { Router, RouterLink } from "@angular/router";
import { VideoService } from '../../services/videoService/video-service';
import { Video } from '../../types/external/video';
import { AuthService } from '../../services/auth/auth-service';
import { StatusError } from '../../types/internal/status-error';

@Component({
  selector: 'app-home',
  imports: [RouterLink],
  templateUrl: './home.html',
  styleUrl: './home.css',
})

export class Home {
  videoService = inject(VideoService);
  authService = inject(AuthService);
  router = inject(Router);

  search = signal("");
  videos = signal(<VideoSearch[]>([]));

  ngOnInit(){

  }
  
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
          let dado:StatusError = {
            status:erro.error.status,
            menssage:erro.error.menssage
          }
          this.router.navigate(['/error'], {state: {dado:dado}})
        }
      })
      
    }
  }

  logout(){
    this.authService.logout();
  }

}
