import { Routes } from '@angular/router';
import { Home } from './components/home/home';
import { CreateIdea } from './components/create-idea/create-idea';

export const routes: Routes = [
    {path:"", component: Home},
    {path:"ideas/new/:id", component:CreateIdea}
    
];
