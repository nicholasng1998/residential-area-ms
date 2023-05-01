import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PaymentRoutingModule } from './payment-routing.module';
import { PaymentPageComponent } from './payment-page/payment-page.component';
import { NG_ZORRO_MODULES } from 'src/app/shared/ng-zorro';

@NgModule({
  declarations: [
    PaymentPageComponent
  ],
  imports: [
    ...NG_ZORRO_MODULES,
    CommonModule,
    PaymentRoutingModule
  ]
})
export class PaymentModule { }
