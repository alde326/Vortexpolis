// src/app/app-routing.module.ts

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MoviesComponent } from './movies/movies.component';
// Importa aquí los otros componentes que quieras agregar

const routes: Routes = [
  { path: 'movies', component: MoviesComponent },
  // { path: 'clientes', component: ClientesComponent }, // agrega cuando los tengas
  // { path: 'compras', component: ComprasComponent }, // agrega cuando los tengas
  { path: '', redirectTo: '/movies', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { } 
