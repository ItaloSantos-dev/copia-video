import { inject, Injectable } from '@angular/core';
import { ApiBack } from '../../api-back/api-back';
import { Idea } from '../../types/internal/idea';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class IdeaService {
  apiBack = inject(ApiBack)


  getMyIdeas():Observable<Idea[]>{
    return this.apiBack.getMyIdeas();
  }

  saveNewIdea(newIdea:Idea):Observable<Idea>{
    return this.apiBack.saveNewIdea(newIdea).pipe();
  }

  deleteIdeaById(id:string){
    console.log(id);
    
    return this.apiBack.deleteIdeaById(id);
  }

  

}
