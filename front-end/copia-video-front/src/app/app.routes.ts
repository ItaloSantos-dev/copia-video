import { Routes } from '@angular/router';
import { Home } from './components/home/home';
import { CreateIdea } from './components/idea/create-idea/create-idea';
import { Login } from './components/auth/login/login';
import { authenticatedGuard } from './security/guards/authenticated-guard';
import { Register } from './components/auth/register/register';
import { AllIdeas } from './components/idea/all-ideas/all-ideas';
import { EditIdea } from './components/idea/edit-idea/edit-idea';
import { Error } from './components/error/error';
import { Dashboard } from './components/admin/dashboard/dashboard';
import { isAdminGuardGuard } from './security/guards/is-admin-guard-guard';
import { ServerMetrics } from './components/admin/dashboard/server-metrics/server-metrics';

export const routes: Routes = [
    {path:"", component: Home},
    {path:"ideas/new/:id", component:CreateIdea, canActivate:[authenticatedGuard]},
    {path:"login", component:Login },
    {path:"register", component:Register},
    {path: "ideas", component:AllIdeas, canActivate:[authenticatedGuard]},
    {path: "ideas/:id", component:EditIdea, canActivate:[authenticatedGuard]},
    {path: "error", component:Error},
    {path:"admin/dashboard", component:Dashboard, canActivate:[authenticatedGuard, isAdminGuardGuard]},
    {path:"admin/dashboard/server-metrics", component:ServerMetrics},
    {path:"**", component:Error}

];
