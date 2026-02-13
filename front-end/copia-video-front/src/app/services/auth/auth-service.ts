import { inject, Injectable } from '@angular/core';
import { ApiBack } from '../../api-back/api-back';
import { LoginDTO } from '../../types/dto/login-dto';
import { Observable } from 'rxjs';
import { User } from '../../types/internal/user';
import { RegisterDTO } from '../../types/dto/register-dto';
import { Router } from '@angular/router';
import {jwtDecode} from 'jwt-decode'

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  apiBack = inject(ApiBack)
  router = inject(Router);


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

  logout(){
    localStorage.clear();
    this.router.navigate(['/'])
  }

  getUserByToken(){
    const token = localStorage.getItem('token') as string;
    return jwtDecode(token);
  }

  getUserRoleBytoken(){
    interface UserAttributes{
      role:string
      sub:string
    }

    const token = localStorage.getItem('token') as string;
    
    return jwtDecode<UserAttributes>(token);

  }

  isAdmin():boolean{
    interface UserAttributes{
      role:string
      sub:string
    }

    const token = localStorage.getItem('token') as string;

    console.log(jwtDecode<UserAttributes>(token).role==="ADMIN");
    
    return jwtDecode<UserAttributes>(token).role==="ADMIN";
  }


}
