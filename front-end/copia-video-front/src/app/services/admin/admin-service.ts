import { inject, Injectable } from '@angular/core';
import { ApiBack } from '../../api-back/api-back';
import { Observable } from 'rxjs';
import { ServerMetrics } from '../../components/admin/dashboard/server-metrics/server-metrics';
import { ReportServerMetrics } from '../../types/internal/report/report-server-metrics';

@Injectable({
  providedIn: 'root',
})
export class AdminService {

  private apiBack = inject(ApiBack);

  getUsersCount(){
    return this.apiBack.getUsersCount();
  }

  getServerMetrics():Observable<ReportServerMetrics>{
    const finalDate = new Date().toISOString().split('T')[0];

    const temp = new Date().setDate(new Date().getDate() - 30);

    const initialDate = new Date(temp).toISOString().split('T')[0];

    console.log("DI: "+ initialDate);

    console.log("DF: " + finalDate);
    
    return this.apiBack.getServerMetrics(initialDate, finalDate);
  }
}
