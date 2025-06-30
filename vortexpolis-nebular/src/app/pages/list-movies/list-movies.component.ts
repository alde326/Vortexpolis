import { Component, OnInit } from '@angular/core';
import { PeliculaService } from 'src/app/services/pelicula.service';
import { Pelicula } from 'src/app/models/pelicula.model';

@Component({
  selector: 'app-list-movies',
  templateUrl: './list-movies.component.html',
  styleUrls: ['./list-movies.component.scss']
})
export class ListMoviesComponent implements OnInit {

  peliculas: Pelicula[] = [];
  isLoading = true;

  constructor(private peliculaService: PeliculaService) {}

  ngOnInit(): void {
    this.obtenerPeliculas();
  }

  obtenerPeliculas() {
    this.peliculaService.getPeliculas().subscribe({
      next: (data) => {
        this.peliculas = data.filter(p => p.estado === true); // solo las activas
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error al obtener películas', err);
        this.isLoading = false;
      }
    });
  }

  deshabilitarPelicula(id: number) {
    console.log('Deshabilitar película con ID:', id);
    this.peliculaService.deshabilitarPelicula(id).subscribe({
      next: () => {
        console.log('Película deshabilitada exitosamente');
        this.obtenerPeliculas(); // refrescar la lista
      },
      error: (err) => console.error('Error al deshabilitar la película:', err)
    });
  }


  editarPelicula(id: number) {
    // Aquí puedes navegar al formulario de edición
    console.log('Editar película con ID:', id);
  }

}
