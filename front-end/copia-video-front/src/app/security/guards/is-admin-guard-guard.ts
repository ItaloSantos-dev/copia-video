import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth-service';
import { StatusError } from '../../types/internal/status-error';
import { HttpStatusCode } from '@angular/common/http';

export const isAdminGuardGuard: CanActivateFn = (route, state) => {

  const router = inject(Router)
  const authService = inject(AuthService)

  const userAttributes =authService.getUserRoleBytoken();

  if(userAttributes.role!=="ADMIN") {
    const dado:StatusError ={
      status:HttpStatusCode.Forbidden,
      menssage:"VOCÊ NÃO POSSUI AUTHORIZAÇÂO PARA ACESSAR ESTA PÁGINA"
    }
    router.navigate(['/error'], {state: {dado:dado}})
  }

  return true;
};
