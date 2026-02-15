import { Component, AfterViewInit, signal, inject } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import * as fabric from 'fabric';
import { Video } from '../../../types/external/video';
import { VideoService } from '../../../services/videoService/video-service';
import { StatusError } from '../../../types/internal/status-error';
import { Idea } from '../../../types/internal/idea';
import { IdeaService } from '../../../services/ideaService/idea-service';

@Component({
  selector: 'app-drawn-board',
  standalone: true,
  templateUrl: './drawn-board.html',
})
export class DrawnBoard implements AfterViewInit {

  ideaService = inject(IdeaService)
  
  sanitizer = inject(DomSanitizer);

  router = inject(Router)

  route = inject(ActivatedRoute);
  

  iframeUrl = signal(<SafeResourceUrl> (""));

  idea = signal(<Idea>({} as Idea));

  ngOnInit(){
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.ideaService.getIdeaById(id).subscribe({
        next:(dado) =>{
          this.idea.set(dado);
          this.iframeUrl.set(this.sanitizer.bypassSecurityTrustResourceUrl(
            'https://www.youtube.com/embed/' + dado.video_id
          ));
          
        },
        error:(erro)=>{
          let dado:StatusError = {
            status:erro.error.status,
            menssage:erro.error.menssage
          }
          this.router.navigate(['/error'], {state: {dado:dado}});
        }
      })
    }
  }







  private canvas?: fabric.Canvas;

  ngAfterViewInit() {
    this.canvas = new fabric.Canvas('hw-canvas', {
      isDrawingMode: true,
      width: 800,
      height: 500,
      backgroundColor: '#ffffff',
    });

    // IMPORTANTE: Instancie o pincel explicitamente
    this.canvas.freeDrawingBrush = new fabric.PencilBrush(this.canvas);
    this.canvas.freeDrawingBrush.width = 4;
    this.canvas.freeDrawingBrush.color = '#000000';
  }

  changeColor(color: string) {
    if (this.canvas?.freeDrawingBrush) {
      this.canvas.freeDrawingBrush.color = color;
    }
  }

  clean(){
    if (this.canvas) {
      this.canvas.clear(); // Remove todos os objetos
      this.canvas.backgroundColor = '#ffffff'; // O clear remove o fundo, então resetamos para branco
      this.canvas.renderAll(); // Força o canvas a desenhar a tela limpa
    }
  }

  exportToPostgres() {
    if (!this.canvas) return;

    // O objeto JSON estruturado para sua coluna JSONB
    const drawingData = this.canvas.toJSON();
    
    console.log('--- JSON PARA SALVAR NO PSQL ---');
    console.log(JSON.stringify(drawingData));
    
    alert('JSON gerado no console! Pronto para o salvar no Postgres.');
  }
}