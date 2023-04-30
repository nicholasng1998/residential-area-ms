import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PageModel } from './resident.service';

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

  findAllEmergencyRequest(pageSize: number, pageNumber: number): Observable<PageModel<EmergencyRequestModel>>{
    let params = new HttpParams();
    params = params.append('pageSize', pageSize);
    params = params.append('pageNumber', pageNumber);
    return this.http.get<PageModel<EmergencyRequestModel>>('api/protected/emergency-request/read', {params});
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
