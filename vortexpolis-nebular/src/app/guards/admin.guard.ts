// src/app/guards/admin.guard.ts
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    if (this.authService.getRol() === 'ROLE_ADMIN') {
      return true;
    } else {
      this.router.navigate(['/movies']); // o redirige donde quieras
      return false;
    }
  }
}