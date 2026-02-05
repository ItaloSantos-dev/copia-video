import { Component, inject, signal } from '@angular/core';
import { CopiaVideoApiService } from '../../services/copia-video-api-service';
import { Video } from '../../types/external/video';
import { ActivatedRoute } from '@angular/router';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Idea } from '../../types/internal/idea';


@Component({
  selector: 'app-create-idea',
  imports: [],
  templateUrl: './create-idea.html',
  styleUrl: './create-idea.css',
})
export class CreateIdea {
  
  
  apiCopiaVideoService = inject(CopiaVideoApiService);
  iframeUrl!:SafeResourceUrl;

  route = inject(ActivatedRoute);
  video = signal(<Video>({} as Video));
  sanitizer = inject(DomSanitizer)


  ngOnInit():void{
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.apiCopiaVideoService.getVideoById(id).subscribe({
        next:(dado)=> {
          console.log("Veio foi aqui" + dado);
          
          this.video.set(dado);
        },
        error:(erro)=>{
          console.log("Deu erro" + erro);
        }
      });

      this.iframeUrl = this.sanitizer.bypassSecurityTrustResourceUrl(
        'https://www.youtube.com/embed/' + id
      );
      console.log(this.iframeUrl); 
    }
  }

  saveNewIdea(_title:string, _annotations:string){
    let newIdea:Idea={
      title: _title,
      annotations: _annotations,
      link_video: "http://youtube.com/watch?v=" + this.video().id
    }
    
    this.apiCopiaVideoService.saveNewIdea(newIdea).subscribe({
      next:(dado)=> {
        console.log("Salvou" + dado);
      },
      error:(erro)=>{
        console.log(erro);
        
      }
    })
    
  
    
    
  }





}
