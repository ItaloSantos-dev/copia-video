import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule} from '@angular/forms';
import { Router, RouterLink } from "@angular/router";
import { RegisterDTO } from '../../../types/dto/register-dto';
import { AuthService } from '../../../services/auth/auth-service';

@Component({
  selector: 'app-register',
  imports: [RouterLink, ReactiveFormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
  authService = inject(AuthService)
  router = inject(Router)

  formRegister = new FormGroup({
    name: new FormControl('',[Validators.required, Validators.minLength(1)]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(8)])
  });

  submit(){
    let newUser:RegisterDTO = {
      name : this.formRegister.get('name')?.value as string,
      email : this.formRegister.get('email')?.value as string,
      password : this.formRegister.get('password')?.value as string
    }

    this.authService.register(newUser).subscribe({
      next:(dado)=>{
        this.router.navigate(['/login']);
      },
      error:(erro) => {
        console.log(erro);
        
      },
    })
  }

  disableBtnRegister():boolean{
    return !(this.formRegister.get('name')?.valid && 
      this.formRegister.get('email')?.valid &&
      this.formRegister.get('password')?.valid)
  }




}
