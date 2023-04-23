import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { throwError } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(request: HttpRequest<any>, next: HttpHandler) {

    const token = sessionStorage.getItem('token');
    if (request.url.toLowerCase().includes('protected')) {
        if (token) {
            const clonedRequest = request.clone({
                headers: request.headers.set('Authorization', `Bearer ${token}`)
            });
            return next.handle(clonedRequest);
        }
        return throwError('Access Denied');
    }
    return next.handle(request);
  }
  
}
