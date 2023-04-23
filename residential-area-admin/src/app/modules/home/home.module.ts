import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './components/home/home.component';
import { NG_ZORRO_MODULES } from 'src/app/shared/ng-zorro';

@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    ...NG_ZORRO_MODULES,
    CommonModule,
    HomeRoutingModule,
  ]
})
export class HomeModule { }
