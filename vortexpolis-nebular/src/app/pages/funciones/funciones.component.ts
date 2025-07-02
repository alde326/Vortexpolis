import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { FuncionesService } from 'src/app/services/funciones.service';
import { PeliculaService } from 'src/app/services/pelicula.service';
import { SalaService } from 'src/app/services/sala.service';
import { CinesService } from 'src/app/services/cine.service';

import { Funcion } from 'src/app/models/funcion.models';
import { Pelicula } from 'src/app/models/pelicula.model';
import { Sala } from 'src/app/models/sala.model';
import { Cine } from 'src/app/models/cines.model';

@Component({
  selector: 'app-funciones',
  templateUrl: './funciones.component.html',
  styleUrls: ['./funciones.component.scss']
})
export class FuncionesComponent implements OnInit {

  peliculas: Pelicula[] = [];
  salas: Sala[] = [];
  cines: Cine[] = [];
  funciones: Funcion[] = [];
  funcionForm: FormGroup;
  editando: boolean = false;

  displayedColumns: string[] = ['pelicula', 'fechaHora', 'salaId', 'precio', 'estado', 'acciones'];

  constructor(
    private funcionesService: FuncionesService,
    private peliculasService: PeliculaService,
    private salaService: SalaService,
    private cineService: CinesService,
    private fb: FormBuilder
  ) {
    this.funcionForm = this.fb.group({
      id: [null],
      fechaHora: ['', Validators.required],
      salaId: ['', Validators.required],
      peliculaId: ['', Validators.required],
      cineId: ['', Validators.required],
      precioEntrada: [1000, [Validators.required, Validators.min(0)]],
      estado: [true]
    });
  }

  ngOnInit(): void {
    this.obtenerFunciones();
    this.obtenerPeliculas();
    this.obtenerSalas();
    this.obtenerCines();
  }

  obtenerPeliculas(): void {
    this.peliculasService.getPeliculas().subscribe(data => {
      this.peliculas = data;
    });
  }

  obtenerSalas(): void {
    this.salaService.listarTodas().subscribe(data => {
      this.salas = data;
    });
  }

  obtenerCines(): void {
    this.cineService.listarTodos().subscribe(data => {
      this.cines = data;
    });
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

  obtenerNombrePelicula(peliculaId: number): string {
    const pelicula = this.peliculas.find(p => p.id === peliculaId);
    return pelicula ? pelicula.titulo : 'Desconocida';
  }
  }
