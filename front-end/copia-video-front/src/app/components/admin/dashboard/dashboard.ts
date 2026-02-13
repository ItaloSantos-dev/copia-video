import { Component, inject, signal } from '@angular/core';
import { AdminService } from '../../../services/admin/admin-service';
import { StatusError } from '../../../types/internal/status-error';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  imports: [RouterLink],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {
  adminService = inject(AdminService)
  usersCount = signal(0)
  router = inject(Router);

  ngOnInit(){
    this.adminService.getUsersCount().subscribe({
      next:(dado)=>{
        this.usersCount.set(dado)
      },
      error:(erro)=>{
        const dado:StatusError ={
          status:erro.error.status,
          menssage:erro.error.menssage
        }
        this.router.navigate(['/error'], {state:{dado:dado}});
      }
    })
  }
}
