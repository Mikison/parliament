import { Injectable, inject } from '@angular/core';
import { environment } from '../enviroments/enviroment-update';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UpdaterService {
  private readonly updaterURL = `${environment.api.baseUrl}`;

  httpClient = inject(HttpClient);

  update(): Observable<String> {
    return this.httpClient.get<String>(this.updaterURL);
  }

  updateAllVotes(): Observable<void> {
    return this.httpClient.get<void>(`${this.updaterURL}/MP/all`);
  }

  updateMPVotesById(id: number): Observable<void> {
    return this.httpClient.get<void>(`${this.updaterURL}/MP/${id}`);
  }
}
