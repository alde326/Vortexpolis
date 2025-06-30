import { Component, OnInit } from '@angular/core';
import { PeliculaService } from '../../services/pelicula.service';
import { Pelicula } from '../../models/pelicula.model';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.scss']
})
export class MoviesComponent implements OnInit {

  peliculas: Pelicula[] = [];
  isLoading = true;

  constructor(private peliculaService: PeliculaService) { }

  ngOnInit(): void {
    this.peliculaService.getPeliculas().subscribe({
      next: (data) => {
        console.log('Películas recibidas:', data); // 👈 Verifica aquí
        this.peliculas = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error al obtener películas:', err);
        this.isLoading = false;
      }
    });
  }

}
