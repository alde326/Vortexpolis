import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MoviesComponent } from './pages/movies/movies.component';
import { CreateMovieComponent } from './pages/create-movie/create-movie.component';
import { ListMoviesComponent } from './pages/list-movies/list-movies.component';


const routes: Routes = [
  { path: '', redirectTo: 'movies', pathMatch: 'full' },
  { path: 'movies', component: MoviesComponent },
  { path: 'create-movie', component: CreateMovieComponent }, 
  { path: 'listpeliculas', component: ListMoviesComponent },
  { path: 'editar-pelicula/:id', component: CreateMovieComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

