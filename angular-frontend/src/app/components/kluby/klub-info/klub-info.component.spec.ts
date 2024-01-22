import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KlubInfoComponent } from './klub-info.component';

describe('KlubInfoComponent', () => {
  let component: KlubInfoComponent;
  let fixture: ComponentFixture<KlubInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KlubInfoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(KlubInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
