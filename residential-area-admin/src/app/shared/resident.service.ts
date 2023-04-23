import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

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

  readResident(): Observable<ResidentResponseModel[]>{
    return this.http.get<ResidentResponseModel[]>('api/protected/resident/read');
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
