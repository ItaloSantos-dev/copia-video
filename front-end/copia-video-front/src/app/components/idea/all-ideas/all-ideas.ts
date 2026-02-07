import { Component, inject, signal } from '@angular/core';
import { IdeaService } from '../../../services/ideaService/idea-service';
import { Idea } from '../../../types/internal/idea';

@Component({
  selector: 'app-all-ideas',
  imports: [],
  templateUrl: './all-ideas.html',
  styleUrl: './all-ideas.css',
})
export class AllIdeas {
  ideaService = inject(IdeaService)
  ideas = signal(<Idea[]>([]))


  ngOnInit(){
    this.loadMyIdeas();
  }

  loadMyIdeas(){
    this.ideaService.getMyIdeas().subscribe({
      next:(dados) => {
        this.ideas.set(dados)
        console.log(dados);
        
        
      },
      error:(erro) => {
        console.log(erro);
        
      }
    })
  }

  deleIdea(id:string){
    console.log("ts: " + id);
    
    this.ideaService.deleteIdeaById(id).subscribe({
      next: ()=>{
        this.loadMyIdeas()
        console.log("Idea apagada com sucesso");  
      },
      error:(erro)=>{
        console.log("Erro ao deletar ideia \n" + erro);
        
      }
    })
    
  }

}
