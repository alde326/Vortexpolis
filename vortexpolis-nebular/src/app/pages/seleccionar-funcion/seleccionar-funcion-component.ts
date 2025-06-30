import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'; // 👈 agrega Router aquí
import { FuncionesService } from 'src/app/services/funciones.service';
import { Funcion } from 'src/app/models/funcion.models';

@Component({
  selector: 'app-seleccionar-funcion',
  templateUrl: './seleccionar-funcion-component.html',
  styleUrls: ['./seleccionar-funcion-component.scss']
})
export class SeleccionarFuncionComponent implements OnInit {

  peliculaId!: number;
  funciones: Funcion[] = [];
  isLoading = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router, // 👈 agrega esto
    private funcionesService: FuncionesService
  ) { }

  ngOnInit(): void {
    this.peliculaId = Number(this.route.snapshot.paramMap.get('peliculaId'));
    this.obtenerFunciones();
  }

  obtenerFunciones(): void {
    this.funcionesService.listarFuncionesPorPelicula(this.peliculaId).subscribe({
      next: (data) => {
        console.log('Funciones recibidas:', data); 
        this.funciones = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error al obtener funciones:', err);
        this.isLoading = false;
      }
    });
  }

  seleccionarFuncion(funcion: Funcion): void {
    console.log('Función seleccionada:', funcion);
    // Aquí puedes navegar a la vista de compra directamente:
    this.router.navigate(['/comprar-entradas', funcion.id]); // 👈 ejemplo
  }
}
