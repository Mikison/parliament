import { Component } from '@angular/core';
import { ButtonComponent } from './button/button.component';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-kluby',
  standalone: true,
  imports: [ButtonComponent, MatIconModule],
  templateUrl: './kluby.component.html',
  styleUrl: './kluby.component.css',
})
export class KlubyComponent {}
