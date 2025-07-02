// src/app/guards/cliente.guard.ts
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ClienteGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    const rol = this.authService.getRol();
    console.log('🛡️ ClienteGuard activado. Rol encontrado: ', rol);

    if (rol === 'ROLE_CLIENTE') {
      console.log('✅ Acceso permitido por ClienteGuard');
      return true;
    } else {
      console.warn('🚫 Acceso denegado por ClienteGuard. Redirigiendo...');
      this.router.navigate(['/movies']);
      return false;
    }
  }
}
