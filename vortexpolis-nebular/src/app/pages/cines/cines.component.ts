import { Component, OnInit } from '@angular/core';
import { CinesService } from 'src/app/services/cine.service';
import { Cine } from 'src/app/models/cines.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-cines',
  templateUrl: './cines.component.html',
  styleUrls: ['./cines.component.scss']
})
export class CinesComponent implements OnInit {

  cines: Cine[] = [];
  cineForm: FormGroup;
  editando: boolean = false;

  displayedColumns: string[] = ['nombre', 'direccion', 'telefono', 'acciones'];

  constructor(private cinesService: CinesService, private fb: FormBuilder) {
    this.cineForm = this.fb.group({
      id: [null],
      nombre: ['', Validators.required],
      direccion: ['', Validators.required],
      telefono: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.obtenerCines();
  }

  obtenerCines(): void {
    this.cinesService.listarTodos().subscribe(data => {
      this.cines = data;
    });
  }

  guardar(): void {
    if (this.editando) {
      this.cinesService.actualizar(this.cineForm.value).subscribe(() => {
        this.obtenerCines();
        this.cancelar();
      });
    } else {
      this.cinesService.crear(this.cineForm.value).subscribe(() => {
        this.obtenerCines();
        this.cineForm.reset();
      });
    }
  }

  editar(cine: Cine): void {
    this.editando = true;
    this.cineForm.patchValue(cine);
  }

  eliminar(id: number): void {
    if (confirm('¿Estás seguro de eliminar este cine?')) {
      this.cinesService.eliminar(id).subscribe(() => {
        this.obtenerCines();
      });
    }
  }

  cancelar(): void {
    this.editando = false;
    this.cineForm.reset();
  }
}

