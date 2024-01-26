import {
  Component,
  OnInit,
  WritableSignal,
  inject,
  signal,
} from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { ClubsService } from '../../../services/clubs.service';
import { ClubDto } from '../../../models/club-dto';
import { Router, RouterModule } from '@angular/router';
import { ParliamentMemberDto } from '../../../models/parliamentMember-dto';

@Component({
  selector: 'app-klub-info',
  standalone: true,
  imports: [MatIconModule, RouterModule],
  templateUrl: './klub-info.component.html',
  styleUrl: './klub-info.component.css',
})
export class KlubInfoComponent implements OnInit {
  clubService = inject(ClubsService);
  route = inject(Router);
  club: WritableSignal<ClubDto> = signal({});
  members: WritableSignal<ParliamentMemberDto[]> = signal([]);

  ngOnInit(): void {
    this.getClubById();
  }

  private getClubById() {
    const clubID = Number(this.route.url.split('/')[2]);
    this.clubService.findClubById(clubID).subscribe((data) => {
      this.club.set(data);
      if (data.members) {
        this.members.set(data.members);
      } else {
        this.members.set([]);
      }
      console.log(data);
    });
  }
}
