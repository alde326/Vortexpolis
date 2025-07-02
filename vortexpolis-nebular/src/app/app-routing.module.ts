// src/app/app-routing.module.ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MoviesComponent } from './pages/movies/movies.component';
import { CreateMovieComponent } from './pages/create-movie/create-movie.component';
import { ListMoviesComponent } from './pages/list-movies/list-movies.component';
import { CinesComponent } from './pages/cines/cines.component';
import { FuncionesComponent } from './pages/funciones/funciones.component';
import { SeleccionarFuncionComponent } from './pages/seleccionar-funcion/seleccionar-funcion-component';
import { FormularioCompraComponent } from './pages/formulario-compra/formulario-compra.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';


import { AdminGuard } from './guards/admin.guard';
import { ClienteGuard } from './guards/cliente.guard';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' }, // ✅ Esta es la que debe quedar
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  { path: 'movies', component: MoviesComponent },

  // 🔒 Solo ADMIN puede crear y editar películas
  { path: 'create-movie', component: CreateMovieComponent, canActivate: [AdminGuard] },
  { path: 'editar-pelicula/:id', component: CreateMovieComponent, canActivate: [AdminGuard] },

  // 🔒 Solo ADMIN puede listar películas y cines
  { path: 'listpeliculas', component: ListMoviesComponent, canActivate: [AdminGuard] },
  { path: 'listcines', component: CinesComponent, canActivate: [AdminGuard] },
  { path: 'listFunciones', component: FuncionesComponent, canActivate: [AdminGuard] },

  // 🔒 Solo CLIENTE puede comprar entradas
  { path: 'comprar-funcion/:peliculaId', component: SeleccionarFuncionComponent, canActivate: [ClienteGuard] },
  { path: 'comprar-entradas/:funcionId', component: FormularioCompraComponent, canActivate: [ClienteGuard] },

  { path: '**', redirectTo: 'login' } // 🔥 Esta es la ruta catch-all
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
