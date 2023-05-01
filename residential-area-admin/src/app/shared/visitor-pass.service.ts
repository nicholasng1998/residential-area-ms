import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PageModel } from './resident.service';

export interface VisitorPassResponseModel {
  uuid: string;
  visitorName: string;
  residentUnit: string;
  status: string;
  residentId: number;
  imageStr: string;
}

@Injectable({
  providedIn: 'root'
})
export class VisitorPassService {

  constructor(private http: HttpClient) { }

  readAll(pageSize: number, pageNumber: number): Observable<PageModel<VisitorPassResponseModel>>{
    let params = new HttpParams();
    params = params.append('pageSize', pageSize);
    params = params.append('pageNumber', pageNumber);
    return this.http.get<PageModel<VisitorPassResponseModel>>('api/protected/visitor-pass/read', {params});
  }
}
