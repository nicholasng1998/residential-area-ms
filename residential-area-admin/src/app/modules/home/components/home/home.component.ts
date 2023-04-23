import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/shared/auth-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  loginForm: FormGroup;
  username = 'username';
  password = 'password';
  loading: boolean = false;

  constructor(private fb: FormBuilder, private authService: AuthServiceService, private router: Router) { 
    this.loginForm = this.fb.group({
      [this.username]: [null, [Validators.required]],
      [this.password]: [null, [Validators.required]]
    });
  }

  ngOnInit(): void {

  }

  login() {
    if(this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      this.loginForm.markAsDirty();
      return;
    }

    return this.authService.login(this.loginForm.controls['username'].value, this.loginForm.controls['password'].value).subscribe((res) => {
      if(res) {
        sessionStorage.setItem('token', res.toString());
        this.router.navigate(['/main/dashboard']);
      } else {
        console.log('login fail');
      }
    });

  }

}
