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
  precioUnitario: number = 10; // Puedes traer esto desde el backend si quieres

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private router: Router,
    private metodoPagoService: MetodoPagoService,
    private comprasService: ComprasService
  ) {
    this.compraForm = this.fb.group({
      cantidad: [1, [Validators.required, Validators.min(1)]],
      metodoPagoId: ['', Validators.required],
      clienteId: ['', Validators.required] // Simulando cliente logueado
    });
  }

  ngOnInit(): void {
    this.funcionId = Number(this.route.snapshot.paramMap.get('funcionId'));
    this.cargarMetodosPago();
  }

  cargarMetodosPago(): void {
    this.metodoPagoService.listarMetodosPago().subscribe(data => {
      this.metodosPago = data;
    });
  }

  calcularTotal(): number {
    return this.precioUnitario * this.compraForm.value.cantidad;
  }

  guardarCompra(): void {
    if (this.compraForm.invalid) return;

    const compraDTO = {
      clienteId: this.compraForm.value.clienteId,
      metodoPagoId: this.compraForm.value.metodoPagoId,
      funcionId: this.funcionId,
      cantidad: this.compraForm.value.cantidad
    };

    this.comprasService.registrarCompra(compraDTO).subscribe(() => {
      alert('Compra realizada con éxito');
      this.router.navigate(['/']);
    });
  }
}
