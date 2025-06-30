import { Component, OnInit } from '@angular/core';
import { PeliculaService } from '../../services/pelicula.service';
import { Pelicula } from '../../models/pelicula.model';
import { Router } from '@angular/router'; // 👈 Esto debes importar

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.scss']
})
export class MoviesComponent implements OnInit {

  peliculas: Pelicula[] = [];
  isLoading = true;

  constructor(
    private peliculaService: PeliculaService,
    private router: Router // 👈 Lo inyectas aquí
  ) { }

  ngOnInit(): void {
    this.peliculaService.getPeliculas().subscribe({
      next: (data) => {
        console.log('Películas recibidas:', data);
        this.peliculas = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error al obtener películas:', err);
        this.isLoading = false;
      }
    });
  }

  seleccionarPelicula(pelicula: Pelicula): void {
    // Aquí navegas a la ruta donde mostrarás las funciones disponibles para esa película
    this.router.navigate(['/comprar-funcion', pelicula.id]);
  }
}
