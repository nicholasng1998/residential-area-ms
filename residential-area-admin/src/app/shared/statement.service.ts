import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StatementService {

  constructor(private http: HttpClient) { }

  getStatement(year: number, month: number): any{
    let params = new HttpParams();
    params = params.append('year', year);
    params = params.append('month', month);
    return this.http.get('api/protected/statement/read', {params, responseType: 'blob'});
  }
}
