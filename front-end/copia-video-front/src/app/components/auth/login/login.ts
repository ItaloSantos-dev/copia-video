import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule} from '@angular/forms';
import { LoginDTO } from '../../../types/dto/login-dto';
import { AuthService } from '../../../services/auth/auth-service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone:true,
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  authService = inject(AuthService)
  router = inject(Router)

  formLogin = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(8)])
  })
  

  disableBtnLogin():boolean{
    return !(this.formLogin.get('email')?.valid === true && this.formLogin.get('password')?.valid == true )
  }

  submit(){
    let loginDto:LoginDTO = {
      email: this.formLogin.get('email')?.value as string,
      password:this.formLogin.get('password')?.value as string
    }

    this.authService.login(loginDto).subscribe({
      next:(token)=>{
        this.authService.saveToken(token);
        this.router.navigate(['/']);
      },
      error:(erro)=>{
        console.log(erro);
      }
    })
  }

}
