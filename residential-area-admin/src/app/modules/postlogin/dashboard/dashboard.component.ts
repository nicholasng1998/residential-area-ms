import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  MENU = [
    {
      title: 'Resident',
      path: ''
    },
    {
      title: 'Payment',
      path: ''
    },
    {
      title: 'Notice Board',
      path: ''
    },
    {
      title: 'Reporting',
      path: ''
    },
    {
      title: 'Visitor',
      path: ''
    },
    {
      title: 'Emergency Request',
      path: ''
    },

  ]

  constructor() { }

  ngOnInit(): void {
  }

}
