import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { MoviesComponent } from './movies/movies.component';
import { AppRoutingModule } from './app-routing.module'; // ✅ Asegúrate que esto esté bien importado

@NgModule({
  declarations: [
    AppComponent,
    MoviesComponent,
    // Agrega aquí los demás componentes cuando los tengas
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule // ✅ Módulo de rutas importado correctamente
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { } // ✅ Asegúrate de tener esta exportación correctamente
