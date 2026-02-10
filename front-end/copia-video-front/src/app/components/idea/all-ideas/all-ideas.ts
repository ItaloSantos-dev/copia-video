import { Component, inject, signal } from '@angular/core';
import { IdeaService } from '../../../services/ideaService/idea-service';
import { Idea } from '../../../types/internal/idea';
import { Router, RouterLink } from "@angular/router";
import { StatusError } from '../../../types/internal/status-error';

@Component({
  selector: 'app-all-ideas',
  imports: [RouterLink],
  templateUrl: './all-ideas.html',
  styleUrl: './all-ideas.css',
})
export class AllIdeas {
  ideaService = inject(IdeaService)
  ideas = signal(<Idea[]>([]))
  router = inject(Router);


  ngOnInit(){
    this.loadMyIdeas();
  }

  loadMyIdeas(){
    this.ideaService.getMyIdeas().subscribe({
      next:(dados) => {
        this.ideas.set(dados);
      },
      error:(erro) => {
        let dado:StatusError = {
          status:erro.error.status,
          menssage:erro.error.menssage
        }
        this.router.navigate(['/error'], {state: {dado:dado}})
        
      }
    })
  }

  deleIdea(id:string){
    console.log("ts: " + id);
    
    this.ideaService.deleteIdeaById(id).subscribe({
      next: ()=>{
        this.loadMyIdeas()  
      },
      error:(erro)=>{
        let dado:StatusError = {
          status:erro.error.status,
          menssage:erro.error.menssage
        }

      }
    })
    
  }

}
