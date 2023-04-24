import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StatementService } from 'src/app/shared/statement.service';

@Component({
  selector: 'app-reporting',
  templateUrl: './reporting.component.html',
  styleUrls: ['./reporting.component.css']
})
export class ReportingComponent implements OnInit {

  constructor(private router: Router, private statementService: StatementService) { }

  ngOnInit(): void {
  }

  routeToDashboard() {
    this.router.navigate(['/main/dashboard']);
  }

  
}
