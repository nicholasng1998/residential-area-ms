import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmergencyRequestModel, EmergencyRequestService } from 'src/app/shared/emergency-request.service';

@Component({
  selector: 'app-emergency-request-management',
  templateUrl: './emergency-request-management.component.html',
  styleUrls: ['./emergency-request-management.component.css']
})
export class EmergencyRequestManagementComponent implements OnInit {

  // pagination
  total = 0;
  pageSize = 10;
  pageNumber = 1;

  constructor(private router: Router, private emergencyRequest: EmergencyRequestService) { }

  data: EmergencyRequestModel[] = [];

  ngOnInit(): void {
    this.emergencyRequest.findAllEmergencyRequest(this.pageSize, this.pageNumber).subscribe((res) => {
      if (res) {
        this.data = res?.content;
        this.total = res?.totalElements;
      }
    });
  }

  routeToDashboard() {
    this.router.navigate(['/main/dashboard']);
  }

  reject(id: number) {
    this.emergencyRequest.rejectEmergencyRequest(id).subscribe((res) => {
      this.ngOnInit();
    });
  }  

  resolved(id: number) {
    this.emergencyRequest.resolveEmergencyRequest(id).subscribe((res) => {
      this.ngOnInit();
    });
  }

  changePage(pageNumber: number) {
    this.pageNumber = pageNumber;
    this.ngOnInit();
  }
}
