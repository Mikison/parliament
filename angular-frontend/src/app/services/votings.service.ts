import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { environment } from '../enviroments/enviroment';
import { Observable } from 'rxjs';
import { VotingDto } from '../models/voting-dto';

@Injectable({
  providedIn: 'root',
})
export class VotingsService {
  private readonly votingURL = `${environment.api.baseUrl}/${environment.api.votings}`;

  http = inject(HttpClient);

  findAllVotings(): Observable<VotingDto[]> {
    return this.http.get<VotingDto[]>(this.votingURL);
  }

  findVotingById(id: number): Observable<VotingDto> {
    return this.http.get<VotingDto>(`${this.votingURL}/${id}`);
  }

  deleteVotingById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.votingURL}/${id}`);
  }

  addVoting(voting: VotingDto): Observable<VotingDto> {
    return this.http.post<VotingDto>(this.votingURL, voting);
  }

  updateVoting(voting: VotingDto): Observable<VotingDto> {
    return this.http.put<VotingDto>(`${this.votingURL}/${voting.id}`, voting);
  }
}
