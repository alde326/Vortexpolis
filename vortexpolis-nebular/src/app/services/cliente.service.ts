import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private apiUrl = 'http://localhost:8080/api/clientes';

  constructor(private http: HttpClient) { }

  // Listar clientes activos
  obtenerClientesActivos(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/activos`, { withCredentials: true });
  }

  // Inhabilitar cliente
  inhabilitarCliente(id: number): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}/inhabilitar`, {}, { withCredentials: true });
  }

  // Consultar compras de un cliente
  obtenerComprasPorCliente(id: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${id}/compras`, { withCredentials: true });
  }
}
