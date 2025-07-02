import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
})
export class AppComponent {

  constructor(private authService: AuthService, private router: Router) {}

  logout() {
    this.authService.logout().subscribe({
      next: () => {
        console.log('✅ Sesión cerrada exitosamente');
        localStorage.removeItem('rol');
        this.router.navigate(['/login']);
      },
      error: (err) => {
        console.error('❌ Error al cerrar sesión', err);
      }
    });
  }

  isAdmin(): boolean {
    return this.authService.getRol() === 'ROLE_ADMIN';
  }

  isCliente(): boolean {
    return this.authService.getRol() === 'ROLE_CLIENTE';
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }
}
