import { inject, Injectable } from '@angular/core';
import { ApiBack } from '../../api-back/api-back';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  apiBack = inject(ApiBack)

  getUsersCount(){
    return this.apiBack.getUsersCount();
  }
}
