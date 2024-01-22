import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GlosowaniaComponent } from './glosowania.component';

describe('GlosowaniaComponent', () => {
  let component: GlosowaniaComponent;
  let fixture: ComponentFixture<GlosowaniaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GlosowaniaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GlosowaniaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
