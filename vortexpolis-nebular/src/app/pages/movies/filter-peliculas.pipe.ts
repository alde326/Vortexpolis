import { Pipe, PipeTransform } from '@angular/core';
import { Pelicula } from '../../models/pelicula.model';

@Pipe({
  name: 'filterPeliculas'
})
export class FilterPeliculasPipe implements PipeTransform {

  transform(peliculas: Pelicula[], filtro: string): Pelicula[] {
    if (!peliculas || !filtro) {
      return peliculas;
    }

    return peliculas.filter(pelicula =>
      pelicula.titulo.toLowerCase().includes(filtro.toLowerCase())
    );
  }

}
