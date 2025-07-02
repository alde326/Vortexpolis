import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Funcion } from '../models/funcion.models';


@Injectable({
  providedIn: 'root'
})
export class FuncionesService {

  private apiUrl = 'http://localhost:8080/api/funciones'; // Ajusta si es necesario

  constructor(private http: HttpClient) { }

  listarTodasLasFunciones() {
    return this.http.get<Funcion[]>('http://localhost:8080/api/funciones', { withCredentials: true });
  }

  crearFuncion(funcion: Funcion): Observable<Funcion> {
    return this.http.post<Funcion>(this.apiUrl, funcion, { withCredentials: true });
  }

  desactivarFuncion(funcion: Funcion): Observable<Funcion> {
    funcion.estado = false;
    return this.actualizarFuncion(funcion);
  }

  listarFuncionesPorPelicula(peliculaId: number): Observable<Funcion[]> {
    return this.http.get<Funcion[]>(`${this.apiUrl}/pelicula/${peliculaId}`, { withCredentials: true });
  }

  actualizarFuncion(funcion: Funcion): Observable<Funcion> {
    return this.http.put<Funcion>(`${this.apiUrl}/${funcion.id}`, funcion, { withCredentials: true });
  }

  obtenerPorId(id: number) {
    return this.http.get<any>(`http://localhost:8080/api/funciones/${id}`, { withCredentials: true });
  }

}
