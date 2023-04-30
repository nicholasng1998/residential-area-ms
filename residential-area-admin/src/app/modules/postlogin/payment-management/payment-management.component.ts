import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PaymentResponseModel, PaymentService } from 'src/app/shared/payment.service';

@Component({
  selector: 'app-payment-management',
  templateUrl: './payment-management.component.html',
  styleUrls: ['./payment-management.component.css']
})
export class PaymentManagementComponent implements OnInit {

  // pagination
  total = 0;
  pageSize = 10;
  pageNumber = 1;

  data: PaymentResponseModel[] = [];

  constructor(private router: Router, private paymentService: PaymentService) { }

  ngOnInit(): void {
    this.paymentService.findAllPayments(this.pageSize, this.pageNumber).subscribe((res) => {
      if (res) {
        this.data = res?.content;
        this.total = res?.totalElements;
      }
    });
  }

  routeToDashboard() {
    this.router.navigate(['/main/dashboard']);
  }

  changePage(pageNumber: number) {
    this.pageNumber = pageNumber;
    this.ngOnInit();
  }

  complete(id: number) {
    this.paymentService.completePayment(id).subscribe((res) => {
      this.ngOnInit();
    });
  }  

  reject(id: number) {
    this.paymentService.completePayment(id).subscribe((res) => {
      this.ngOnInit();
    });
  }
}
