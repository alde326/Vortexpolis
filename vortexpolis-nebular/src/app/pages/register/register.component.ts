import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  cliente = {
    nombre: '',
    apellido: '',
    email: '',
    telefono: '',
    password: ''
  };

  mensaje: string = '';
  error: string = '';

  constructor(private http: HttpClient) {}

  registrar() {
    this.http.post('http://localhost:8080/api/public/register', this.cliente, { withCredentials: true })
      .subscribe({
        next: (res) => {
          this.mensaje = 'Registro exitoso. Ahora puedes iniciar sesión.';
          this.error = '';
          this.cliente = { nombre: '', apellido: '', email: '', telefono: '', password: '' };
        },
        error: (err) => {
          if (err.status === 409) {
            this.error = 'El correo ya está registrado.';
          } else {
            this.error = 'Ocurrió un error al registrar el cliente.';
          }
          this.mensaje = '';
        }
      });
  }
}
