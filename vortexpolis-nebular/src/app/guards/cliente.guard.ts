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
    if (this.authService.getRol() === 'ROLE_CLIENTE') {
      return true;
    } else {
      this.router.navigate(['/movies']);
      return false;
    }
  }
}