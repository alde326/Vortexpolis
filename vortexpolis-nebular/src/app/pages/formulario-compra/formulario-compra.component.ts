import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FuncionesService } from 'src/app/services/funciones.service';
import { MetodoPagoService } from 'src/app/services/metodo-pago.service';
import { ComprasService } from 'src/app/services/compras.service';

@Component({
  selector: 'app-formulario-compra',
  templateUrl: './formulario-compra.component.html',
  styleUrls: ['./formulario-compra.component.scss']
})
export class FormularioCompraComponent implements OnInit {

  compraForm: FormGroup;
  funcionId!: number;
  metodosPago: any[] = [];
  precioEntradaSeleccionado: number = 0; // Precio que traeremos de la función

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private router: Router,
    private funcionesService: FuncionesService,
    private metodoPagoService: MetodoPagoService,
    private comprasService: ComprasService
  ) {
    this.compraForm = this.fb.group({
      cantidad: [1, [Validators.required, Validators.min(1)]],
      metodoPagoId: ['', Validators.required],
      clienteId: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.funcionId = Number(this.route.snapshot.paramMap.get('funcionId'));
    this.cargarFuncion();
    this.cargarMetodosPago();
  }

  cargarFuncion(): void {
    this.funcionesService.obtenerPorId(this.funcionId).subscribe({
      next: (funcion: any) => {
        this.precioEntradaSeleccionado = funcion.precioEntrada;
      },
      error: (err: any) => {
        console.error('Error al cargar la función:', err);
      }
    });
  }

  cargarMetodosPago(): void {
    this.metodoPagoService.listarMetodosPago().subscribe({
      next: (data) => {
        this.metodosPago = data;
      },
      error: (err) => {
        console.error('Error al cargar métodos de pago:', err);
      }
    });
  }

  get totalCompra(): number {
    return this.precioEntradaSeleccionado * this.compraForm.value.cantidad;
  }

  guardarCompra(): void {
    if (this.compraForm.invalid) return;

    const compraDTO = {
      clienteId: this.compraForm.value.clienteId,
      metodoPagoId: this.compraForm.value.metodoPagoId,
      funcionId: this.funcionId,
      cantidad: this.compraForm.value.cantidad
    };
    console.log('Datos a enviar:', compraDTO);
    this.comprasService.registrarCompra(compraDTO).subscribe(() => {
      alert('Compra realizada con éxito');
      this.router.navigate(['/']);
    });
  }
}
