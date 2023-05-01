import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VisitorPassResponseModel, VisitorPassService } from 'src/app/shared/visitor-pass.service';

@Component({
  selector: 'app-visitor-management',
  templateUrl: './visitor-management.component.html',
  styleUrls: ['./visitor-management.component.css']
})
export class VisitorManagementComponent implements OnInit {

  // pagination
  total = 0;
  pageSize = 10;
  pageNumber = 1;

  constructor(private router: Router, private visitorPassService: VisitorPassService) { }

  data: VisitorPassResponseModel[] = [];

  ngOnInit(): void {
    this.visitorPassService.readAll(this.pageSize, this.pageNumber).subscribe(res => {
      if (res) {
        this.data = res?.content;
        this.total = res?.totalElements;
      }
    });
  }
  
  routeToDashboard() {
    this.router.navigate(['/main/dashboard']);
  }
}
