import { Component, OnInit } from '@angular/core';
import { Pelicula } from './pelicula.model';
import { PeliculaService } from '../services/pelicula.service';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {

  peliculas: Pelicula[] = []; // 👉 Esta es la que debe aparecer en el HTML

  constructor(private peliculaService: PeliculaService) { }

  ngOnInit(): void {
    this.cargarPeliculas();
  }

  cargarPeliculas() {
    this.peliculaService.getPeliculas().subscribe(data => {
      console.log('Películas recibidas:', data); // 👉 Agrega este log para confirmar
      this.peliculas = data;
    });
  }
}