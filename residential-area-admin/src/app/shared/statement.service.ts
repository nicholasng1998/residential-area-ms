import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StatementService {

  constructor(private http: HttpClient) { }

  getStatement(year: number, month: number): any{
    return this.http.get<any>('api/protected/visitor-pass/read');
  }
}
