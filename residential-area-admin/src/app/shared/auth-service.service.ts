import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor(private http: HttpClient) { }

  login(username: string, password: string) {
    let formData: FormData = new FormData();
    formData.append('username', username);  
    formData.append('password', password);
    return this.http.post('api/login', formData);
  }
}
