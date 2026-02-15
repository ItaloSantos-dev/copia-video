import { Component, inject, signal } from '@angular/core';
import { Idea } from '../../../types/internal/idea';
import { IdeaService } from '../../../services/ideaService/idea-service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { StatusError } from '../../../types/internal/status-error';

@Component({
  selector: 'app-edit-idea',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './edit-idea.html',
  styleUrl: './edit-idea.css',
})
export class EditIdea {
  idea = signal(<Idea> ({} as Idea))

  ideaService = inject(IdeaService)

  iframeUrl = signal(<SafeResourceUrl> (""));

  sanitizer = inject(DomSanitizer)

  route = inject(ActivatedRoute);

  router = inject(Router)

  editForm = new FormGroup({
    'title': new FormControl('',[Validators.required]),
    'annotations': new FormControl('',[Validators.required]),
  });

  ngOnInit(){
    const id = this.route.snapshot.paramMap.get('id')
    if(id){
      this.ideaService.getIdeaById(id).subscribe({
        next:(dado) => {
          this.idea.set(dado)

          this.iframeUrl.set(this.sanitizer.bypassSecurityTrustResourceUrl(
            'https://www.youtube.com/embed/' + dado.video_id
          ));

          this.editForm.patchValue({
            title:dado.title,
            annotations:dado.annotations
          });
        },
        error:(erro)=>{
          let dado:StatusError = {
            status:erro.error.status,
            menssage:erro.error.menssage
          }
          this.router.navigate(['/error'], {state: {dado: dado}})
          
        },
      })
    }
  }

  submit(){
    let idea:Idea = {
      title:this.editForm.get('title')?.value as string,
      annotations:this.editForm.get('annotations')?.value as string
    }
    this.ideaService.updateIdeaById(this.idea().id!, idea).subscribe({
      next:(dado)=> {
        this.router.navigate(["/ideas"]);
      },
      error:(erro) => {
        let dado:StatusError = {
          status:erro.error.status,
          menssage:erro.error.menssage
        }
        this.router.navigate(['/error'], {state: {dado: dado}});
        
      },
    })
  }


}
