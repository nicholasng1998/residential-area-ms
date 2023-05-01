import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/shared/auth-service.service';

@Component({
  selector: 'app-payment-page',
  templateUrl: './payment-page.component.html',
  styleUrls: ['./payment-page.component.css']
})
export class PaymentPageComponent implements OnInit {
  ngOnInit(): void {
  }
  page = 1;
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

  login() {
    this.page ++;
  }
}
