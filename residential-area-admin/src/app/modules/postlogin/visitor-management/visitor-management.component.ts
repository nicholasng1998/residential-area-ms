import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VisitorPassResponseModel, VisitorPassService } from 'src/app/shared/visitor-pass.service';

@Component({
  selector: 'app-visitor-management',
  templateUrl: './visitor-management.component.html',
  styleUrls: ['./visitor-management.component.css']
})
export class VisitorManagementComponent implements OnInit {

  constructor(private router: Router, private visitorPassService: VisitorPassService) { }

  data: VisitorPassResponseModel[] = [];

  ngOnInit(): void {
    this.visitorPassService.readAll().subscribe(res => {
      this.data = res;
    });
  }
  
  routeToDashboard() {
    this.router.navigate(['/main/dashboard']);
  }
}
