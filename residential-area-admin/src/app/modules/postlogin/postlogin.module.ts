import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PostloginRoutingModule } from './postlogin-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NG_ZORRO_MODULES } from 'src/app/shared/ng-zorro';
import { PostloginComponent } from './postlogin/postlogin.component';
import { ResidentAccountComponent } from './resident-account/resident-account.component';
import { NoticeBoardComponent } from './notice-board/notice-board.component';
import { PaymentManagementComponent } from './payment-management/payment-management.component';
import { ReportingComponent } from './reporting/reporting.component';
import { EmergencyRequestManagementComponent } from './emergency-request-management/emergency-request-management.component';
import { VisitorManagementComponent } from './visitor-management/visitor-management.component';

@NgModule({
  declarations: [
    DashboardComponent,
    PostloginComponent,
    ResidentAccountComponent,
    NoticeBoardComponent,
    PaymentManagementComponent,
    ReportingComponent,
    EmergencyRequestManagementComponent,
    VisitorManagementComponent
  ],
  imports: [
    ...NG_ZORRO_MODULES,
    CommonModule,
    PostloginRoutingModule
  ]
})
export class PostloginModule { }
