import { Routes } from '@angular/router';
import { Home } from './components/home/home';
import { CreateIdea } from './components/create-idea/create-idea';
import { Login } from './components/auth/login/login';
import { authenticatedGuard } from './security/guards/authenticated-guard';
import { Register } from './components/auth/register/register/register';

export const routes: Routes = [
    {path:"", component: Home},
    {path:"ideas/new/:id", component:CreateIdea, canActivate:[authenticatedGuard]},
    {path:"login", component:Login },
    {path:"register", component:Register}
    
];
