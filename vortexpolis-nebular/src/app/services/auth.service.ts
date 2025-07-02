import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/public/login'; // Tu ruta de login backend

  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    const body = new HttpParams()
      .set('username', email)
      .set('password', password);

    return this.http.post(this.apiUrl, body.toString(), { // 👈 quita el <any>
      headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' }),
      withCredentials: true,
      responseType: 'text' // 👈 agrega esta línea
    });
  }


  guardarRol(rol: string) {
    localStorage.setItem('rol', rol);
  }

  getRol(): string | null {
    return localStorage.getItem('rol');
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('rol');
  }

  getUserInfo(): Observable<any> {
    return this.http.get<any>('http://localhost:8080/api/public/auth/user', { withCredentials: true });
  }

  logout() {
    return this.http.post('http://localhost:8080/api/public/logout', {}, {
      withCredentials: true,
      responseType: 'text'
    });
  }
}
