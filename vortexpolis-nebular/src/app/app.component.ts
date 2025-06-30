import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
})
export class AppComponent {
  menuItems = [
    {
      title: 'Dashboard',
      icon: 'home-outline',
      link: '/dashboard',
    },
    {
      title: 'Películas',
      icon: 'film-outline',
      link: '/movies',
    },
    {
      title: 'Usuarios',
      icon: 'people-outline',
      link: '/users',
    },
    {
      title: 'Configuración',
      icon: 'settings-outline',
      link: '/settings',
    },
  ];
}