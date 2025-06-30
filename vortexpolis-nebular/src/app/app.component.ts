import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'vortexpolis-nebular';

  menuItems = [
    { title: 'Películas', link: '/movies' }
  ];
}
