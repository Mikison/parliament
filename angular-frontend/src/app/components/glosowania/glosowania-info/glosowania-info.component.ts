import { VotesDto } from './../../../models/votes-dto';
import {
  Component,
  OnInit,
  WritableSignal,
  inject,
  signal,
} from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { VotingsService } from '../../../services/votings.service';
import { Router, RouterModule } from '@angular/router';
import { VotingDto } from '../../../models/voting-dto';

@Component({
  selector: 'app-glosowania-info',
  standalone: true,
  imports: [MatIconModule, RouterModule],
  templateUrl: './glosowania-info.component.html',
  styleUrl: './glosowania-info.component.css',
})
export class GlosowaniaInfoComponent implements OnInit {
  votingsService = inject(VotingsService);
  router = inject(Router);

  voting: WritableSignal<VotingDto> = signal({});
  participants: WritableSignal<VotesDto[]> = signal([]);

  ngOnInit(): void {
    this.getVotes();
  }

  private getVotes(): void {
    const votingID = Number(this.router.url.split('/')[2]);
    this.votingsService.findVotingById(votingID).subscribe((data) => {
      this.voting.set(data);
      if (data.participants) {
        this.participants.set(data.participants);
      } else {
        this.participants.set([]);
      }
      console.log(this.voting());
    });
  }
}
