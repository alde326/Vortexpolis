import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MoviesComponent } from './pages/movies/movies.component';
// import { ClientesComponent } from './clientes/clientes.component';
// import { ComprasComponent } from './compras/compras.component';

const routes: Routes = [
  { path: 'movies', component: MoviesComponent },
  // { path: 'clientes', component: ClientesComponent },
  // { path: 'compras', component: ComprasComponent },
  { path: '', redirectTo: 'movies', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule { }
