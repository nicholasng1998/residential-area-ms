import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EmergencyRequestManagementComponent } from './emergency-request-management/emergency-request-management.component';
import { NoticeBoardComponent } from './notice-board/notice-board.component';
import { PaymentManagementComponent } from './payment-management/payment-management.component';
import { PostloginComponent } from './postlogin/postlogin.component';
import { ReportingComponent } from './reporting/reporting.component';
import { ResidentAccountComponent } from './resident-account/resident-account.component';
import { VisitorManagementComponent } from './visitor-management/visitor-management.component';

const routes: Routes = [
  {
    path: '',
    component: PostloginComponent,
    children: [
      {
        path: 'dashboard',
        component: DashboardComponent,
      },
      {
        path: 'resident-account',
        component: ResidentAccountComponent
      },
      {
        path: 'notice-board',
        component: NoticeBoardComponent
      },
      {
        path: 'payment-management',
        component: PaymentManagementComponent
      },
      {
        path: 'reporting',
        component: ReportingComponent
      },
      {
        path: 'visitor-management',
        component: VisitorManagementComponent
      },
      {
        path: 'emergency-request-management',
        component: EmergencyRequestManagementComponent
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PostloginRoutingModule { }
