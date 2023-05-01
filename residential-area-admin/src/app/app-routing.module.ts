import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: 'home',
    loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule)
  },
  {
    path: 'main',
    loadChildren: () => import('./modules/postlogin/postlogin.module').then(m => m.PostloginModule)
  },
  // {
  //   path: 'payment',
  //   loadChildren: () => import('./modules/payment/payment.module').then(m => m.PaymentModule)
  // }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
