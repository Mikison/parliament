import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GlosowaniaInfoComponent } from './glosowania-info.component';

describe('GlosowaniaInfoComponent', () => {
  let component: GlosowaniaInfoComponent;
  let fixture: ComponentFixture<GlosowaniaInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GlosowaniaInfoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GlosowaniaInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
