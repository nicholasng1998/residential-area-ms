import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-postlogin',
  templateUrl: './postlogin.component.html',
  styleUrls: ['./postlogin.component.css']
})
export class PostloginComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  logout() {
    sessionStorage.removeItem("token");
    this.router.navigate(['/home']);
  }
}
