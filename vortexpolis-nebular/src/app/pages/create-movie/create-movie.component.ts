import { Component } from '@angular/core';
import { PeliculaService } from '../../services/pelicula.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-create-movie',
  templateUrl: './create-movie.component.html',
  styleUrls: ['./create-movie.component.css']
})
export class CreateMovieComponent {
  pelicula = { titulo: '', descripcion: '', imagenUrl: '' };
  selectedFile!: File;

  constructor(
    private peliculaService: PeliculaService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  crearPelicula() {
    const pelicula = {
      titulo: this.pelicula.titulo,
      descripcion: this.pelicula.descripcion
      // Agrega otros campos si es necesario
    };

    this.peliculaService.crearPelicula(pelicula).subscribe(
      response => {
        console.log('Película creada:', response);
        // Aquí puedes redirigir o mostrar mensaje de éxito
      },
      error => {
        console.error('Error al crear película:', error);
      }
    );
  }
}
