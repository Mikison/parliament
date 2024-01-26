import {
  Component,
  OnInit,
  WritableSignal,
  inject,
  signal,
} from '@angular/core';
import { ButtonComponent } from './button/button.component';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { ClubsService } from '../../services/clubs.service';
import { ClubDto } from '../../models/club-dto';

@Component({
  selector: 'app-kluby',
  standalone: true,
  imports: [ButtonComponent, MatIconModule, RouterModule],
  templateUrl: './kluby.component.html',
  styleUrl: './kluby.component.css',
})
export class KlubyComponent implements OnInit {
  clubService = inject(ClubsService);

  clubs: WritableSignal<ClubDto[]> = signal([]);

  ngOnInit(): void {
    this.findAllClubs();
  }

  private findAllClubs() {
    this.clubService.findAllClubs().subscribe((data) => {
      this.clubs.set(data);
      console.log(data);
    });
  }
}
