import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { StatementService } from 'src/app/shared/statement.service';

@Component({
  selector: 'app-reporting',
  templateUrl: './reporting.component.html',
  styleUrls: ['./reporting.component.css']
})
export class ReportingComponent implements OnInit {

  month = 'month';
  year = 'year';
  generatePdfForm: FormGroup;
  constructor(private router: Router, private statementService: StatementService, private fb: FormBuilder) {
    this.generatePdfForm = this.fb.group({
      [this.month]: [null, [Validators.required]],
      [this.year]: [null, [Validators.required]]
    });
   }
  ngOnInit(): void {
  }

  routeToDashboard() {
    this.router.navigate(['/main/dashboard']);
  }

  generatePdf() {
    if (this.generatePdfForm.valid) {
      let yearValue = this.generatePdfForm.controls[this.year].value;
      let monthValue = this.generatePdfForm.controls[this.month].value;
      this.statementService.getStatement(yearValue, monthValue).subscribe((res: any) => {
        const downloadLink = document.createElement('a');
        downloadLink.href = URL.createObjectURL(res);
        downloadLink.download = 'statement_'+ yearValue + '_' + monthValue + '.pdf';
        downloadLink.click();
      });
    }
  }

  numericPressOnly(event: KeyboardEvent) {
    const pattern = /[0-9]/;
    const inputChar = String.fromCharCode(event.charCode);
  
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }
}
