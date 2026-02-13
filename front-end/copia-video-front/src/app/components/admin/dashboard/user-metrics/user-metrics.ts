import { Component, inject, signal } from '@angular/core';
import { ReportUserMetrics } from '../../../../types/internal/report/report-user-metrics';
import { AdminService } from '../../../../services/admin/admin-service';
import { Router, RouterLink } from '@angular/router';
import { StatusError } from '../../../../types/internal/status-error';

@Component({
  selector: 'app-user-metrics',
  imports: [RouterLink],
  templateUrl: './user-metrics.html',
  styleUrl: './user-metrics.css',
})
export class UserMetrics {
  reportUserMetrics = signal(<ReportUserMetrics>({} as ReportUserMetrics))
  adminService = inject(AdminService)
  router = inject(Router)

  ngOnInit(){
    this.adminService.getUserMetrics().subscribe({
      next:(dado) => {
        this.reportUserMetrics.set(dado);
        console.log(this.reportUserMetrics());
        
      },
      error:(erro)=>{
        let dado:StatusError ={
          status:erro.error.status,
          menssage:erro.error.menssage
        }
        this.router.navigate(['/error'], {state:{dado:dado}})
      }
    })
  }
}
