import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NbCardModule, NbButtonModule, NbThemeModule, NbLayoutModule } from '@nebular/theme';
import { MoviesComponent } from './pages/movies/movies.component';

import { PagesRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent,
    MoviesComponent,
  ],
  imports: [
    BrowserModule,
    PagesRoutingModule,
    NbCardModule, // ✅ Importante
    NbButtonModule, // ✅ Importante
    NbLayoutModule, // ✅ Recomendado para la estructura
    NbThemeModule.forRoot({ name: 'default' }) // ✅ Configuración del tema
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
