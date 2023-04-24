import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ResidentResponseModel, ResidentService } from 'src/app/shared/resident.service';

@Component({
  selector: 'app-resident-account',
  templateUrl: './resident-account.component.html',
  styleUrls: ['./resident-account.component.css']
})
export class ResidentAccountComponent implements OnInit {

  // pagination
  total = 0;
  pageSize = 10;
  pageNumber = 1;

  showModal = false;
  editShowModal = false;
  createResidentForm: FormGroup;
  editResidentForm: FormGroup;
  id = 'id';
  name = 'name';
  age = 'age';
  email = 'email';
  phoneNo = 'phoneNo';
  unitNo = 'unitNo';
  username = 'username';
  password = 'password';
  data: ResidentResponseModel[] = [];
  selectedData: ResidentResponseModel = {} as ResidentResponseModel;

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
    this.editResidentForm = this.fb.group({
      [this.id]: [null, [Validators.required]],
      [this.name]: [null, [Validators.required]],
      [this.age]: [null, [Validators.required]],
      [this.email]: [null, [Validators.required]],
      [this.phoneNo]: [null, [Validators.required]],
      [this.unitNo]: [null, [Validators.required]],
    });
  }

  ngOnInit(): void {
    this.residentService.readResident(this.pageSize, this.pageNumber).subscribe(res => {
      if (res) {
        this.data = res?.content;
        this.total = res?.totalElements;
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

  openEditModal(id: number) {
    this.selectedData = this.data.filter(d => d.id === id)[0];
    this.editShowModal = true;
  }

  closeEditModal() {
    this.editShowModal = false;
  }

  editResident() {
    this.residentService.updateResident(this.editResidentForm.value).subscribe((res: string) => {
      this.closeModal();
      this.ngOnInit();
    });
  }

  changePage(pageNumber: number) {
    this.pageNumber = pageNumber;
    this.ngOnInit();
  }
}
