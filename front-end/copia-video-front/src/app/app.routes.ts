import { Routes } from '@angular/router';
import { Home } from './components/home/home';
import { CreateIdea } from './components/idea/create-idea/create-idea';
import { Login } from './components/auth/login/login';
import { authenticatedGuard } from './security/guards/authenticated-guard';
import { Register } from './components/auth/register/register/register';
import { AllIdeas } from './components/idea/all-ideas/all-ideas';

export const routes: Routes = [
    {path:"", component: Home},
    {path:"ideas/new/:id", component:CreateIdea, canActivate:[authenticatedGuard]},
    {path:"login", component:Login },
    {path:"register", component:Register},
    {path: "ideas", component:AllIdeas, canActivate:[authenticatedGuard]}
];
