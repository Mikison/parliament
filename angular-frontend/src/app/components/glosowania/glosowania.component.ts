import {
  Component,
  OnInit,
  WritableSignal,
  inject,
  signal,
} from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { VotingsService } from '../../services/votings.service';
import { VotingDto } from '../../models/voting-dto';

@Component({
  selector: 'app-glosowania',
  standalone: true,
  imports: [MatIconModule, RouterModule],
  templateUrl: './glosowania.component.html',
  styleUrl: './glosowania.component.css',
})
export class GlosowaniaComponent implements OnInit {
  votingService = inject(VotingsService);

  votings: WritableSignal<VotingDto[]> = signal([]);

  ngOnInit(): void {
    this.getAllVotins();
  }

  private getAllVotins() {
    this.votingService.findAllVotings().subscribe((votings) => {
      // Sort votings by date in descending order
      votings.sort((a: VotingDto, b: VotingDto) => {
        if (a.date && b.date) {
          return new Date(b.date).getTime() - new Date(a.date).getTime();
        }
        return 0;
      });

      this.votings.set(votings);
      console.log(votings);
    });
  }

  filterResults(text: string): void {
    const results: VotingDto[] = [];
    for (const member of this.votings()) {
      if (
        (member.title &&
          member.title.toLowerCase().indexOf(text.toLowerCase()) !== -1) ||
        (member.date &&
          member.date.toLowerCase().indexOf(text.toLowerCase()) !== -1) ||
        (member.id &&
          member.id.toString().toLowerCase().indexOf(text.toLowerCase()) !== -1)
      ) {
        results.push(member);
      }
    }
    this.votings.set(results);
    if (results.length === 0 || !text) {
      this.getAllVotins();
    }
  }
}
