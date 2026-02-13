import { Component, inject, signal } from '@angular/core';
import { AdminService } from '../../../../services/admin/admin-service';
import { StatusError } from '../../../../types/internal/status-error';
import { Router, RouterLink } from '@angular/router';
import { ReportServerMetrics } from '../../../../types/internal/report/report-server-metrics';

@Component({
  selector: 'app-server-metrics',
  imports: [RouterLink],
  templateUrl: './server-metrics.html',
  styleUrl: './server-metrics.css',
})
export class ServerMetrics {
  reportServerMetrics = signal(<ReportServerMetrics>({} as ReportServerMetrics))
  adminService = inject(AdminService)
  router = inject(Router)

  ngOnInit(){    
   this.adminService.getServerMetrics().subscribe({
    next:(dado)=>{
      this.reportServerMetrics.set(dado)
      console.log(this.reportServerMetrics().requestMetrics);
    },
    error:(erro)=>{
      let dado:StatusError ={
        status:erro.error.status,
        menssage:erro.error.menssage
      }
      this.router.navigate(['/error'], {state:{dado:dado}});
    }
   })
  }
}
