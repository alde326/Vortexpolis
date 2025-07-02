import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {

  email = '';
  password = '';
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
    const rol = localStorage.getItem('rol');
    if (rol) {
      this.router.navigate(['/movies']);
    }
  }

  login() {
    console.log('📨 Intentando login con: ', this.email, this.password);

    this.authService.login(this.email, this.password).subscribe({
      next: () => {
        console.log('✅ Login exitoso, consultando el rol...');
        this.authService.getUserInfo().subscribe({
          next: (userInfo) => {
            console.log('ℹ️ Rol recibido: ', userInfo);
            const rol = userInfo[0]?.authority; // Ejemplo: 'ROLE_ADMIN'
            this.authService.guardarRol(rol);
            console.log('Rol guardado:', rol);

            if (rol === 'ADMIN') {  // 🔥 Corrección aquí
              alert('Bienvenido administrador');
              window.location.href = '/'; // 🔄 Forzamos recarga elegante
            } else if (rol === 'CLIENTE') {  // 🔥 Corrección aquí
              alert('Bienvenido cliente');
              window.location.href = '/'; // 🔄 Forzamos recarga elegante
            } else {
              this.errorMessage = 'Rol desconocido';
            }
          }
        });
      },
      error: (err) => {
        console.error('❌ Error en login: ', err);
        this.errorMessage = 'Credenciales incorrectas';
      }
    });
  }
}
