import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ComprasService {

  private apiUrl = 'http://localhost:8080/api/compras';

  constructor(private http: HttpClient) { }

  registrarCompra(compraDTO: any) {
    return this.http.post<any>(this.apiUrl, compraDTO, { withCredentials: true });
  }

  obtenerComprasPorCliente(clienteId: number): Observable<any[]> {
    return this.http.get<any[]>(`http://localhost:8080/api/clientes/${clienteId}/compras`, { withCredentials: true });
  }


}