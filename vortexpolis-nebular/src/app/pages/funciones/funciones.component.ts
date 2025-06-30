import { Component, OnInit, Input } from '@angular/core';
import { FuncionesService } from 'src/app/services/funciones.service';
import { Funcion } from 'src/app/models/funcion.models';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-funciones',
  templateUrl: './funciones.component.html',
  styleUrls: ['./funciones.component.scss']
})
export class FuncionesComponent implements OnInit {

  funciones: Funcion[] = [];
  funcionForm: FormGroup;
  editando: boolean = false;

  displayedColumns: string[] = ['fechaHora', 'salaId', 'estado', 'acciones'];

  constructor(private funcionesService: FuncionesService, private fb: FormBuilder) {
    this.funcionForm = this.fb.group({
      id: [null],
      fechaHora: ['', Validators.required],
      salaId: ['', Validators.required],
      peliculaId: ['', Validators.required],
      cineId: ['', Validators.required],
      estado: [true]
    });
  }

  ngOnInit(): void {
    this.obtenerFunciones();
  }


  obtenerFunciones(): void {
    this.funcionesService.listarTodasLasFunciones().subscribe(data => {
      this.funciones = data;
    });
  }

  guardar(): void {
    if (this.editando) {
      this.funcionesService.actualizarFuncion(this.funcionForm.value).subscribe(() => {
        this.obtenerFunciones();
        this.cancelar();
      });
    } else {
      this.funcionesService.crearFuncion(this.funcionForm.value).subscribe(() => {
        this.obtenerFunciones();
        this.funcionForm.reset({ estado: true });
      });
    }
  }


  editar(funcion: Funcion): void {
    this.editando = true;
    this.funcionForm.patchValue(funcion);
  }

  desactivar(funcion: Funcion): void {
    if (confirm('¿Estás seguro de desactivar esta función?')) {
      this.funcionesService.desactivarFuncion(funcion).subscribe(() => {
        this.obtenerFunciones();
      });
    }
  }

  cancelar(): void {
    this.editando = false;
    this.funcionForm.reset({ estado: true });
  }
}
