import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeleccionarFuncionComponentComponent } from './seleccionar-funcion-component.component';

describe('SeleccionarFuncionComponentComponent', () => {
  let component: SeleccionarFuncionComponentComponent;
  let fixture: ComponentFixture<SeleccionarFuncionComponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SeleccionarFuncionComponentComponent]
    });
    fixture = TestBed.createComponent(SeleccionarFuncionComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
