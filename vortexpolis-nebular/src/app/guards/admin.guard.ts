import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(): boolean {
    const rol = (localStorage.getItem('rol') || '').trim();
    console.log('🛡️ AdminGuard activado. Rol encontrado: ', `"${rol}"`);

    if (rol !== 'ADMIN') {
      console.log('🚫 Acceso denegado por AdminGuard. Redirigiendo...');
      this.router.navigate(['/login']);
      return false;
    }

    console.log('✅ Acceso permitido por AdminGuard.');
    return true;
  }
}
