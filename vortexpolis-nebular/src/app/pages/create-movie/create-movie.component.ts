import { Component } from '@angular/core';
import { PeliculaService } from '../../services/pelicula.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-create-movie',
  templateUrl: './create-movie.component.html',
  styleUrls: ['./create-movie.component.css']
})
export class CreateMovieComponent {
  pelicula = { titulo: '', descripcion: '', imagenUrl: '', estado: true };
  selectedFile!: File;
  movieId?: number;
  esModoEdicion: boolean = false;

  constructor(
    private peliculaService: PeliculaService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    console.log('Archivo seleccionado:', this.selectedFile);
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const idParam = params.get('id');
      if (idParam) {
        this.esModoEdicion = true;
        this.movieId = +idParam;
        this.cargarPelicula(this.movieId);
      } else {
        this.esModoEdicion = false;
      }
    });
  }


  crearPelicula() {
    const formData = new FormData();

    const peliculaJson = JSON.stringify({
      titulo: this.pelicula.titulo,
      descripcion: this.pelicula.descripcion,
      imagenUrl: this.pelicula.imagenUrl, // Puede ser cadena vacía ''
      estado: this.pelicula.estado // ✅ Este es el campo correcto
    });

    formData.append('pelicula', peliculaJson);
    formData.append('imagen', this.selectedFile);

    this.peliculaService.crearPelicula(formData).subscribe(
      response => {
        console.log('Película creada:', response);
        this.router.navigate(['/']);
      },
      error => {
        console.error('Error al crear película:', error);
      }
    );
  }


  cargarPelicula(id: number) {
    this.peliculaService.getPeliculaById(id).subscribe(pelicula => {
      this.pelicula = pelicula; // Asignas el modelo al formulario
    });
  }

  onSubmit() {
    if (this.esModoEdicion && this.movieId) {
      // Aquí puedes adaptar si quieres actualizar con imagen después
      this.peliculaService.actualizarPelicula(this.movieId, this.pelicula).subscribe(() => {
        console.log('Película actualizada');
        this.router.navigate(['/peliculas']);
      });
    } else {
      this.crearPelicula();
    }
  }

}
