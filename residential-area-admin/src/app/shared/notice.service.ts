import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PageModel } from './resident.service';

export interface NoticeResponseModel {
  id: number;
  title: string;
  content: string;
  active: boolean;
  expiryDate: Date;
}

export interface NoticeCreateRequestBody {
  title: string;
  content: string;
  isActive: boolean;
  expiryDate: Date;
}

@Injectable({
  providedIn: 'root'
})
export class NoticeService {

  constructor(private http: HttpClient) { }

  findAllNotices(pageSize: number, pageNumber: number): Observable<PageModel<NoticeResponseModel>>{
    let params = new HttpParams();
    params = params.append('pageSize', pageSize);
    params = params.append('pageNumber', pageNumber);
    return this.http.get<PageModel<NoticeResponseModel>>('api/protected/notice/read-all', {params});
  }

  createNotice(requestBody: NoticeCreateRequestBody): Observable<string> {
    return this.http.post<string>('api/protected/notice/create', requestBody);
  }

  updateNotice(requestBody: NoticeCreateRequestBody): Observable<string> {
    return this.http.post<string>('api/protected/notice/update', requestBody);
  }

  deactivate(id: number): Observable<string> {
    let params = new HttpParams();
    params = params.append('id', id);
    return this.http.post<string>('api/protected/notice/deactivate', params);
  }

  activate(id: number): Observable<string> {
    let params = new HttpParams();
    params = params.append('id', id);
    return this.http.post<string>('api/protected/notice/activate', params);
  }
}
