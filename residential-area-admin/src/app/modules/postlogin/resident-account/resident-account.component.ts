import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ResidentialResponseModel, ResidentService } from 'src/app/shared/resident.service';

@Component({
  selector: 'app-resident-account',
  templateUrl: './resident-account.component.html',
  styleUrls: ['./resident-account.component.css']
})
export class ResidentAccountComponent implements OnInit {

  showModal = false;
  createResidentForm: FormGroup;
  name = 'name';
  age = 'age';
  email = 'email';
  phoneNo = 'phoneNo';
  unitNo = 'unitNo';
  username = 'username';
  password = 'password';
  data: ResidentialResponseModel[] = [];

  constructor(private router: Router, private fb: FormBuilder, private residentService: ResidentService) { 
    this.createResidentForm = this.fb.group({
      [this.username]: [null, [Validators.required]],
      [this.password]: [null, [Validators.required]],
      [this.name]: [null, [Validators.required]],
      [this.age]: [null, [Validators.required]],
      [this.email]: [null, [Validators.required]],
      [this.phoneNo]: [null, [Validators.required]],
      [this.unitNo]: [null, [Validators.required]],
    });
  }

  ngOnInit(): void {
    this.residentService.readResident().subscribe(res => {
      if (res) {
        console.log(res);
        this.data = res;
      }
    });
  }

  routeToDashboard() {
    this.router.navigate(['/main/dashboard']);
  }

  openModal() {
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
  }

  createResident() {
    this.residentService.createResident(this.createResidentForm.value).subscribe((res: string) => {
      this.closeModal();
      this.ngOnInit();
    });
  }

  deleteResident(id: number) {
    this.residentService.deleteResident(id).subscribe((res: string) => {
      this.closeModal();
      this.ngOnInit();
    });
  }
}
