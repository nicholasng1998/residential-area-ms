import { HttpClient } from '@angular/common/http';
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
}
