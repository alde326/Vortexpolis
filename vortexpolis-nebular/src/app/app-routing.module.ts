import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MoviesComponent } from './pages/movies/movies.component';
import { CreateMovieComponent } from './pages/create-movie/create-movie.component';
import { ListMoviesComponent } from './pages/list-movies/list-movies.component';
import { CinesComponent } from './pages/cines/cines.component';
import { FuncionesComponent } from './pages/funciones/funciones.component';
import { SeleccionarFuncionComponent } from './pages/seleccionar-funcion/seleccionar-funcion-component';
import { FormularioCompraComponent } from './pages/formulario-compra/formulario-compra.component';

const routes: Routes = [
  { path: '', redirectTo: 'movies', pathMatch: 'full' },
  { path: 'movies', component: MoviesComponent },
  { path: 'create-movie', component: CreateMovieComponent }, 
  { path: 'listpeliculas', component: ListMoviesComponent },
  { path: 'editar-pelicula/:id', component: CreateMovieComponent },
  { path: 'listcines', component: CinesComponent },
  { path: 'listFunciones', component: FuncionesComponent },
  { path: 'comprar-funcion/:peliculaId', component: SeleccionarFuncionComponent },
  { path: 'comprar-entradas/:funcionId', component: FormularioCompraComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

