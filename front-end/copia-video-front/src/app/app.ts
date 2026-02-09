import { Component, inject, signal } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { AuthService } from './services/auth/auth-service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  authService = inject(AuthService)
  protected readonly title = signal('copia-video-front');

  mobileMenuOpen = false;

  toggleMobileMenu() {
      this.mobileMenuOpen = !this.mobileMenuOpen;
  }

  logout(){
    this.authService.logout();
  }
  
}
