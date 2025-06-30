import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cine } from '../models/cines.model';

@Injectable({
  providedIn: 'root'
})
export class CinesService {

  private apiUrl = 'http://localhost:8080/api/cines'; // Ajusta si es necesario

  constructor(private http: HttpClient) { }

  listarTodos(): Observable<Cine[]> {
    return this.http.get<Cine[]>(this.apiUrl);
  }

  obtenerPorId(id: number): Observable<Cine> {
    return this.http.get<Cine>(`${this.apiUrl}/${id}`);
  }

  crear(cine: Cine): Observable<Cine> {
    return this.http.post<Cine>(this.apiUrl, cine);
  }

  actualizar(cine: Cine): Observable<Cine> {
    return this.http.put<Cine>(`${this.apiUrl}/${cine.id}`, cine);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
