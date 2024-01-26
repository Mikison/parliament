import { ParliamentMemberDto } from './../../../models/parliamentMember-dto';
import {
  Component,
  inject,
  OnInit,
  signal,
  WritableSignal,
} from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MemberService } from '../../../services/member.service';
import { Router, RouterModule } from '@angular/router';
import { VotesDto } from '../../../models/votes-dto';
import { map, Observable } from 'rxjs';

@Component({
  selector: 'app-poslowie-info',
  standalone: true,
  imports: [MatIconModule, RouterModule],
  templateUrl: './poslowie-info.component.html',
  styleUrl: './poslowie-info.component.css',
})
export class PoslowieInfoComponent implements OnInit {
  membersService = inject(MemberService);
  router = inject(Router);

  member: WritableSignal<ParliamentMemberDto> = signal({});
  votes: WritableSignal<VotesDto[]> = signal([]);

  ngOnInit(): void {
    this.getMember();
  }

  private getMember(): void {
    const memberId = Number(this.router.url.split('/')[2]);
    this.membersService.findMemberById(memberId).subscribe((data) => {
      if (data.votes) {
        this.getVotes().subscribe((votes) => {
          votes.sort((a: VotesDto, b: VotesDto) => {
            const aDate = a.date ? new Date(a.date).getTime() : 0;
            const bDate = b.date ? new Date(b.date).getTime() : 0;
            return bDate - aDate;
          });
          this.votes.set(votes);
        });
      }

      this.member.set(data);
      console.log(this.member());
    });
  }

  private getVotes(): Observable<VotesDto[]> {
    const memberId = Number(this.router.url.split('/')[2]);
    return this.membersService.findMemberById(memberId).pipe(
      map((data) => {
        if (data.votes) {
          return data.votes;
        }
        return [];
      })
    );
  }

  filterResults(text: string): void {
    const results: VotesDto[] = [];
    for (const member of this.votes()) {
      if (
        (member.voteTitle &&
          member.voteTitle.toLowerCase().indexOf(text.toLowerCase()) !== -1) ||
        (member.date &&
          member.date.toString().toLowerCase().indexOf(text.toLowerCase()) !==
            -1)
      ) {
        results.push(member);
      }
    }
    this.votes.set(results);
    if (results.length === 0 || !text) {
      this.getMember();
    }
  }
}
