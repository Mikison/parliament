import { Injectable } from '@angular/core';
import { environment } from '../enviroments/enviroment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ClubDto } from '../models/club-dto';

@Injectable({
  providedIn: 'root',
  deps: [HttpClient],
})
export class ClubsService {
  private readonly clubURL = `${environment.api.baseUrl}/${environment.api.clubs}`;

  constructor(private http: HttpClient) {}

  findAllClubs(): Observable<ClubDto[]> {
    console.log(this.clubURL);
    return this.http.get<ClubDto[]>(this.clubURL);
  }

  findClubById(id: number): Observable<ClubDto> {
    return this.http.get<ClubDto>(`${this.clubURL}/${id}`);
  }

  deleteClubById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.clubURL}/${id}`);
  }

  addClub(club: ClubDto): Observable<ClubDto> {
    return this.http.post<ClubDto>(this.clubURL, club);
  }

  updateClub(club: ClubDto): Observable<ClubDto> {
    return this.http.put<ClubDto>(`${this.clubURL}/${club.id}`, club);
  }
}
