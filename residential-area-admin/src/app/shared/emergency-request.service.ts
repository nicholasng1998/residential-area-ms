import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface EmergencyRequestModel {
  id: number;
  title: string;
  message: string;
  status: string;
  residentName: string;
  unitNo: string;
}

@Injectable({
  providedIn: 'root'
})
export class EmergencyRequestService {

  constructor(private http: HttpClient) { }

  findAllEmergencyRequest(): Observable<EmergencyRequestModel[]>{
    return this.http.get<EmergencyRequestModel[]>('api/protected/emergency-request/read');
  }

  resolveEmergencyRequest(id: number): Observable<string> {
    let params = new HttpParams();
    params = params.append('id', id);
    return this.http.post<string>('api/protected/emergency-request/resolve', {}, {params});
  }

  rejectEmergencyRequest(id: number): Observable<string> {
    let params = new HttpParams();
    params = params.append('id', id);
    return this.http.post<string>('api/protected/emergency-request/reject', {}, {params});
  }
}
