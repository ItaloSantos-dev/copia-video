import { HttpHandlerFn, HttpInterceptorFn, HttpRequest } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { AuthService } from "../services/auth/auth-service";
import { Observable } from "rxjs";

export const authInterceptor:HttpInterceptorFn = ( req:HttpRequest<any>, next:HttpHandlerFn) =>{
    const  authService = inject(AuthService);

    const token = authService.getToken();
    if(token!== null){
        const newRequest = req.clone({setHeaders: {"Authorization": "Bearer " + token}});
        return next(newRequest);
    }
    return next(req);

}
