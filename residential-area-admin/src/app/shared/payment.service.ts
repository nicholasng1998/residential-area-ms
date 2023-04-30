import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PageModel } from './resident.service';

export interface PaymentResponseModel {
  id: number;
  amount: number;
  method: string;
  reference: string;
  status: string;
}

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http: HttpClient) { }

  findAllPayments(pageSize: number, pageNumber: number): Observable<PageModel<PaymentResponseModel>>{
    let params = new HttpParams();
    params = params.append('pageSize', pageSize);
    params = params.append('pageNumber', pageNumber);
    return this.http.get<PageModel<PaymentResponseModel>>('api/protected/payment/read', {params});
  }

  completePayment(id: number): Observable<string> {
    let params = new HttpParams();
    params = params.append('id', id);
    return this.http.post<string>('api/protected/payment/complete', {}, {params});
  }

  rejectPayment(id: number): Observable<string> {
    let params = new HttpParams();
    params = params.append('id', id);
    return this.http.post<string>('api/protected/payment/reject', {}, {params});
  }
}
