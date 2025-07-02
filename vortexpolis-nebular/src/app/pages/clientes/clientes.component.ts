import { Component, OnInit } from '@angular/core';
import { ClienteService } from 'src/app/services/cliente.service';
import { ComprasService } from 'src/app/services/compras.service';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.scss']
})
export class ClientesComponent implements OnInit {

  clientes: any[] = [];
  compras: any[] = []; // 👉 Esta propiedad es necesaria
  displayedColumns: string[] = ['nombre', 'email', 'telefono', 'acciones'];

  constructor(private clienteService: ClienteService, private comprasService: ComprasService) {}

  ngOnInit(): void {
    this.cargarClientes();
  }

  cargarClientes(): void {
    this.clienteService.obtenerClientesActivos().subscribe({
      next: (data) => this.clientes = data,
      error: (err) => console.error('Error al cargar clientes', err)
    });
  }

  inhabilitarCliente(clienteId: number): void {
    if (confirm('¿Estás seguro de que quieres inhabilitar este cliente?')) {
      this.clienteService.inhabilitarCliente(clienteId).subscribe({
        next: () => {
          alert('Cliente inhabilitado correctamente');
          this.cargarClientes();
        },
        error: (err) => console.error('Error al inhabilitar cliente', err)
      });
    }
  }

  verCompras(clienteId: number): void { // 👉 Este método es necesario
    this.comprasService.obtenerComprasPorCliente(clienteId).subscribe({
      next: (data) => this.compras = data,
      error: (err) => console.error('Error al cargar compras', err)
    });
  }
}
