import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComprasClienteComponent } from './compras-cliente.component';

describe('ComprasClienteComponent', () => {
  let component: ComprasClienteComponent;
  let fixture: ComponentFixture<ComprasClienteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ComprasClienteComponent]
    });
    fixture = TestBed.createComponent(ComprasClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
