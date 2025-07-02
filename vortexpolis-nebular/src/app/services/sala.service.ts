import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Sala } from '../models/sala.model';

@Injectable({
  providedIn: 'root'
})
export class SalaService {

  private apiUrl = 'http://localhost:8080/api/salas'; // Cambia la URL si es diferente

  constructor(private http: HttpClient) { }

  listarTodas(): Observable<Sala[]> {
    return this.http.get<Sala[]>(this.apiUrl, { withCredentials: true });
  }
}
