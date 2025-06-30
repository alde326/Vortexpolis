import { Component, OnInit } from '@angular/core';
import { PeliculaService } from '../../services/pelicula.service';
import { Pelicula } from '../../models/pelicula.model';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {

  peliculas: Pelicula[] = [];

  constructor(private peliculaService: PeliculaService) { }

  ngOnInit(): void {
    console.log('Componente cargado');
    this.cargarPeliculas();
  }

  cargarPeliculas() {
    this.peliculaService.getPeliculas().subscribe(data => {
      console.log('Películas recibidas:', data);
      this.peliculas = data;
    });
  }
}
