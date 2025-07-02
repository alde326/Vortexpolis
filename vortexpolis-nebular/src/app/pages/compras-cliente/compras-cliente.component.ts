import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ComprasService } from 'src/app/services/compras.service';

@Component({
  selector: 'app-compras-cliente',
  templateUrl: './compras-cliente.component.html',
  styleUrls: ['./compras-cliente.component.scss']
})
export class ComprasClienteComponent implements OnInit {

  compras: any[] = [];
  clienteId!: number;
  displayedColumns: string[] = ['id', 'cantidad', 'total'];

  constructor(
    private route: ActivatedRoute,
    private comprasService: ComprasService
  ) {}

  ngOnInit(): void {
    this.clienteId = Number(this.route.snapshot.paramMap.get('id'));
    this.cargarCompras();
  }

  cargarCompras(): void {
    this.comprasService.obtenerComprasPorCliente(this.clienteId).subscribe({
      next: (data) => this.compras = data,
      error: (err) => console.error('Error al cargar compras', err)
    });
  }
}
