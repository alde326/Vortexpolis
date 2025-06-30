import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MetodoPagoService {

  private apiUrl = 'http://localhost:8080/api/metodos-pago';

  constructor(private http: HttpClient) { }

  listarMetodosPago() {
    return this.http.get<any[]>(this.apiUrl);
  }
}