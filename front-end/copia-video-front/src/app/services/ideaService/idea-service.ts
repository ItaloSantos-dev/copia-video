import { inject, Injectable } from '@angular/core';
import { ApiBack } from '../../api-back/api-back';
import { Idea } from '../../types/internal/idea';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class IdeaService {
  apiBack = inject(ApiBack)

  saveNewIdea(newIdea:Idea):Observable<Idea>{
    return this.apiBack.saveNewIdea(newIdea).pipe();
  }

  

}
