import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface PageModel<T> {
  content: T[];
  size: number;
  totalPages: number;
  numberOfElements: number;
  totalElements: number;
}

export interface ResidentResponseModel {
  id: number;
  name: string;
  age: number;
  email: string;
  phoneNo: string;
  unitNo: string;
  status: string;
}

@Injectable({
  providedIn: 'root'
})
export class ResidentService {

  constructor(private http: HttpClient) { }

  createResident(requestModal: any): Observable<string> {
    return this.http.post<string>('api/protected/resident/create', requestModal);
  }

  readResident(pageSize: number, pageNumber: number): Observable<PageModel<ResidentResponseModel>>{
    let params = new HttpParams();
    params = params.append('pageSize', pageSize);
    params = params.append('pageNumber', pageNumber);
    return this.http.get<PageModel<ResidentResponseModel>>('api/protected/resident/read', {params});
  }

  deleteResident(id: number): Observable<string>{
    let params = new HttpParams();
    params = params.append('id', id);
    return this.http.delete<string>('api/protected/resident/delete', {params});
  }

  updateResident(requestModel: any): Observable<string> {
    return this.http.post<string>('api/protected/resident/update', requestModel);
  }
}
