import {
  Component,
  OnInit,
  WritableSignal,
  inject,
  signal,
} from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { ButtonComponent } from '../kluby/button/button.component';
import { MemberService } from '../../services/member.service';
import { ParliamentMemberDto } from '../../models/parliamentMember-dto';
import { RouterModule } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-poslowie',
  standalone: true,
  imports: [MatIconModule, ButtonComponent, RouterModule],
  templateUrl: './poslowie.component.html',
  styleUrl: './poslowie.component.css',
})
export class PoslowieComponent implements OnInit {
  memberService = inject(MemberService);
  members: WritableSignal<ParliamentMemberDto[]> = signal([]);
  dialog = inject(MatDialog);

  ngOnInit(): void {
    this.findAllMembers();
  }

  private findAllMembers(): void {
    this.memberService.findAllMembers().subscribe((data) => {
      data.sort((a: ParliamentMemberDto, b: ParliamentMemberDto) => {
        if (a.apiID && b.apiID) {
          return a.apiID - b.apiID;
        }
        return 0;
      });
      this.members.set(data);
      console.log(data);
    });
  }

  filterResults(text: string): void {
    const results: ParliamentMemberDto[] = [];
    for (const member of this.members()) {
      if (
        (member.firstLastName &&
          member.firstLastName.toLowerCase().indexOf(text.toLowerCase()) !==
            -1) ||
        (member.club &&
          member.club.toLowerCase().indexOf(text.toLowerCase()) !== -1) ||
        (member.apiID &&
          member.apiID.toString().toLowerCase().indexOf(text.toLowerCase()) !==
            -1)
      ) {
        results.push(member);
      }
    }
    this.members.set(results);
    if (results.length === 0 || !text) {
      this.findAllMembers();
    }
  }
}
