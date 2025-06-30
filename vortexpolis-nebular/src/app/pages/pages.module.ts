import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppComponent } from '../app.component';
import { AppRoutingModule } from './pages-routing.module';
import { MoviesComponent } from './movies/movies.component';
import { ClientesComponent } from './clientes/clientes.component';
import { ComprasComponent } from './compras/compras.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { CreateMovieComponent } from './create-movie/create-movie.component';
import { ListMoviesComponent } from './list-movies/list-movies.component';


@NgModule({
  declarations: [
    AppComponent,
    MoviesComponent,
    ClientesComponent,
    ComprasComponent,
    CreateMovieComponent,
    ListMoviesComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class PagesModule { }
