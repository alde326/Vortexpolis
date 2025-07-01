import { Component, OnInit } from '@angular/core';
import { PeliculaService } from '../../services/pelicula.service';
import { Pelicula } from '../../models/pelicula.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.scss']
})
export class MoviesComponent implements OnInit {

  peliculas: Pelicula[] = [];
  isLoading = true;
  filtro: string = ''; // 👉 Filtro agregado

  constructor(
    private peliculaService: PeliculaService,
    private router: Router
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
    this.router.navigate(['/comprar-funcion', pelicula.id]);
  }
}
