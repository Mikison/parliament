import { Component } from '@angular/core';
import { ButtonComponent } from './button/button.component';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-kluby',
  standalone: true,
  imports: [ButtonComponent, MatIconModule, RouterModule],
  templateUrl: './kluby.component.html',
  styleUrl: './kluby.component.css',
})
export class KlubyComponent {}
