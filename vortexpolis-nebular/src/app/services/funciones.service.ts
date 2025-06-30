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
    return this.http.get<Funcion[]>('http://localhost:8080/api/funciones');
    }


  crearFuncion(funcion: Funcion): Observable<Funcion> {
    return this.http.post<Funcion>(this.apiUrl, funcion);
  }

  listarFuncionesPorPelicula(peliculaId: number): Observable<Funcion[]> {
    return this.http.get<Funcion[]>(`${this.apiUrl}/pelicula/${peliculaId}`);
  }

  actualizarFuncion(funcion: Funcion): Observable<Funcion> {
    return this.http.put<Funcion>(`${this.apiUrl}/${funcion.id}`, funcion);
  }

  desactivarFuncion(funcion: Funcion): Observable<Funcion> {
    // Vamos a simular la desactivación actualizando el campo 'estado' a false
    funcion.estado = false;
    return this.actualizarFuncion(funcion);
  }
}
