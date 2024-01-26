import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoslowieInfoComponent } from './poslowie-info.component';

describe('PoslowieInfoComponent', () => {
  let component: PoslowieInfoComponent;
  let fixture: ComponentFixture<PoslowieInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PoslowieInfoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PoslowieInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
