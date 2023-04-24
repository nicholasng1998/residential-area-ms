import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface VisitorPassResponseModel {
  uuid: string;
  visitorName: string;
  residentUnit: string;
  status: string;
  residentId: number;
}

@Injectable({
  providedIn: 'root'
})
export class VisitorPassService {

  constructor(private http: HttpClient) { }

  readAll(): Observable<VisitorPassResponseModel[]>{
    return this.http.get<VisitorPassResponseModel[]>('api/protected/visitor-pass/read');
  }
}
