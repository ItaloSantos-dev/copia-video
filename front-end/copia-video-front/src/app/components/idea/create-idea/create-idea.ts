import { Component, inject, signal } from '@angular/core';
import { Video } from '../../../types/external/video';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Idea } from '../../../types/internal/idea';
import { IdeaService } from '../../../services/ideaService/idea-service';
import { VideoService } from '../../../services/videoService/video-service';


@Component({
  selector: 'app-create-idea',
  imports: [],
  templateUrl: './create-idea.html',
  styleUrl: './create-idea.css',
})
export class CreateIdea {
  
  ideaService = inject(IdeaService);
  videoService = inject(VideoService);

  iframeUrl!:SafeResourceUrl;

  route = inject(ActivatedRoute);

  router = inject(Router)

  video = signal(<Video>({} as Video));

  sanitizer = inject(DomSanitizer)



  ngOnInit():void{
    
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.videoService.getVideoById(id).subscribe({
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
    console.log(_title);
    
    let newIdea:Idea={
      title: _title,
      annotations: _annotations,
      video_id:  this.video().id
    }
    
    this.ideaService.saveNewIdea(newIdea).subscribe({
      next:(dado)=> {
        this.router.navigate(['/ideas'])
      },
      error:(erro)=>{
        console.log(erro);
        
      }
    })
  }





}
