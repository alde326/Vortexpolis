import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
})
export class AppComponent {

  rol: string | null = null;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {
    this.rol = localStorage.getItem('rol');
  }

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
}
