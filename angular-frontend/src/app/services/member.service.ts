import { ParliamentMemberDto } from './../models/parliamentMember-dto';
import { Injectable, inject } from '@angular/core';
import { environment } from '../enviroments/enviroment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MemberService {
  private readonly memberURL = `${environment.api.baseUrl}/${environment.api.members}`;

  http = inject(HttpClient);

  findAllMembers(): Observable<ParliamentMemberDto[]> {
    return this.http.get<ParliamentMemberDto[]>(this.memberURL);
  }

  findMemberById(id: number): Observable<ParliamentMemberDto> {
    return this.http.get<ParliamentMemberDto>(`${this.memberURL}/${id}`);
  }

  deleteMemberById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.memberURL}/${id}`);
  }

  addMember(member: ParliamentMemberDto): Observable<ParliamentMemberDto> {
    return this.http.post<ParliamentMemberDto>(this.memberURL, member);
  }

  updateMember(member: ParliamentMemberDto): Observable<ParliamentMemberDto> {
    return this.http.put<ParliamentMemberDto>(
      `${this.memberURL}/${member.apiID}`,
      member
    );
  }
}
