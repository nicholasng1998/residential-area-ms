import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NoticeResponseModel, NoticeService } from 'src/app/shared/notice.service';

@Component({
  selector: 'app-notice-board',
  templateUrl: './notice-board.component.html',
  styleUrls: ['./notice-board.component.css']
})
export class NoticeBoardComponent implements OnInit {

  // pagination
  total = 0;
  pageSize = 10;
  pageNumber = 1;

  noticeForm: FormGroup;
  showModal = false;
  title = 'title';
  content = 'content';
  isActive = 'isActive';
  expiryDate = 'expiryDate';
  activationValue = true;
  expiryDateValue = new Date;
  data: NoticeResponseModel[] = [];

  selectedPreviewNotice = 1;
  showPreviewModal = false;
  selectedPreviewContent = '';

  selectedEditNotice: NoticeResponseModel = {} as NoticeResponseModel;
  showEditPreviewModal = false;

  constructor(private router: Router, private fb: FormBuilder, private noticeService: NoticeService) { 
    this.noticeForm = this.fb.group({
      [this.title]: [null, [Validators.required]],
      [this.content]: [null, [Validators.required]],
      [this.isActive]: [null, [Validators.required]],
      [this.expiryDate]: [null, [Validators.required]],
    });
  }

  ngOnInit(): void {
    this.noticeService.findAllNotices(this.pageSize, this.pageNumber).subscribe(res => {
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

  onChange(result: Date): void {
    console.log('onChange: ', result);
  }

  createNotice(): void {
    this.noticeService.createNotice(this.noticeForm.value).subscribe(res => {
      if(res) {
        this.closeModal();
        this.ngOnInit();
      }
    }); 
  }

  changePage(pageNumber: number) {
    this.pageNumber = pageNumber;
    this.ngOnInit();
  }

  preview(id: number) {
    this.selectedPreviewNotice = id;
    this.selectedPreviewContent = this.data.filter(d => d.id === id)[0].content;
    this.showPreviewModal = true;
  }

  closePreviewModal() {
    this.showPreviewModal = false;
  }

  openEditModal(id: number) {
    this.selectedEditNotice = this.data.filter(d => d.id === id)[0];
    this.showEditPreviewModal = false;
  }

  deactivate(id: number) {
    
  }
}
