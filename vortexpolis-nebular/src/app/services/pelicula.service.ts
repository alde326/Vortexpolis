import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pelicula } from '../models/pelicula.model';

@Injectable({
  providedIn: 'root'
})
export class PeliculaService {

  private apiUrl = 'http://localhost:8080/api/peliculas'; // Cambia si es necesario

  constructor(private http: HttpClient) { }

  // Obtener todas las películas
  getPeliculas() {
    return this.http.get<any[]>('http://localhost:8080/api/peliculas', { withCredentials: true });
  }

  getPeliculaById(id: number): Observable<Pelicula> {
    return this.http.get<Pelicula>(`${this.apiUrl}/${id}`, { withCredentials: true });
  }

  crearPelicula(peliculaData: FormData) {
    return this.http.post('http://localhost:8080/api/peliculas', peliculaData, { withCredentials: true });
  }

  actualizarPelicula(id: number, pelicula: Pelicula): Observable<Pelicula> {
    return this.http.put<Pelicula>(`${this.apiUrl}/${id}`, pelicula, { withCredentials: true });
  }

  deshabilitarPelicula(id: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${id}/deshabilitar`, {}, { withCredentials: true });
  }

}
