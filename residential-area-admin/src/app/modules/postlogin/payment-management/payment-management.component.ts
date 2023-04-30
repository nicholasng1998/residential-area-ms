import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-payment-management',
  templateUrl: './payment-management.component.html',
  styleUrls: ['./payment-management.component.css']
})
export class PaymentManagementComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  routeToDashboard() {
    this.router.navigate(['/main/dashboard']);
  }
}
