import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ComprasService {

  private apiUrl = 'http://localhost:8080/api/compras';

  constructor(private http: HttpClient) { }

  registrarCompra(compraDTO: any) {
    return this.http.post<any>(this.apiUrl, compraDTO);
  }
}