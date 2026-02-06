import { inject, Injectable } from '@angular/core';
import { ApiBack } from '../../api-back/api-back';
import { LoginDTO } from '../../types/dto/login-dto';
import { Observable } from 'rxjs';
import { User } from '../../types/internal/user';
import { RegisterDTO } from '../../types/dto/register-dto';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  apiBack = inject(ApiBack)

  login(user:LoginDTO):Observable<string>{
    return this.apiBack.login(user).pipe();
  }

  saveToken(token:string){
    localStorage.setItem('token', token);
  }

  getToken():string{
    return localStorage.getItem('token') as string;
  }


  isLogged():boolean{
    const token = localStorage.getItem('token');
    
    if(token === null) return false;

    if(this.tokenExpirated(token)){
      localStorage.clear()
      return false;
    }

    return true;
  }

  register(newUser:RegisterDTO):Observable<User>{
    return this.apiBack.register(newUser);
  }

  tokenExpirated(token:string):boolean{
    const payload = JSON.parse(atob(token.split('.')[1]));
    const now = Date.now() / 1000;
    
    return payload.exp < now;
  }
}
