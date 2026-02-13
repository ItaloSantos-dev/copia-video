import { Component, signal } from '@angular/core';
import { StatusError } from '../../types/internal/status-error';
import { RouterLink } from "@angular/router";
import { HttpStatusCode } from '@angular/common/http';

@Component({
  selector: 'app-error',
  imports: [RouterLink],
  templateUrl: './error.html',
  styleUrl: './error.css',
})
export class Error {
  error = signal(<StatusError>({} as StatusError))
  
  ngOnInit(){
    history.state.dado===undefined?
    this.error.set({status: HttpStatusCode.NotFound, menssage:"Página não encontrada"}):
    this.error.set(history.state.dado)
  }
}
