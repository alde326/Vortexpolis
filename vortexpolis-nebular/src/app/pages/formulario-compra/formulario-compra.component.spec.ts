import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioCompraComponent } from './formulario-compra.component';

describe('FormularioCompraComponent', () => {
  let component: FormularioCompraComponent;
  let fixture: ComponentFixture<FormularioCompraComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormularioCompraComponent]
    });
    fixture = TestBed.createComponent(FormularioCompraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
