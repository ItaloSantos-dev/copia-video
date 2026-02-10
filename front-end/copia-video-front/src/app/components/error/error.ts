import { Component, signal } from '@angular/core';
import { StatusError } from '../../types/internal/status-error';
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-error',
  imports: [RouterLink],
  templateUrl: './error.html',
  styleUrl: './error.css',
})
export class Error {
  error = signal(<StatusError>({} as StatusError))

  ngOnInit(){
    this.error.set(history.state.dado)
  }
}
