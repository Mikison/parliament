import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoslowieComponent } from './poslowie.component';

describe('PoslowieComponent', () => {
  let component: PoslowieComponent;
  let fixture: ComponentFixture<PoslowieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PoslowieComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PoslowieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
