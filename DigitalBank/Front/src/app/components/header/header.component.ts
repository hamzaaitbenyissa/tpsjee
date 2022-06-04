import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  CurrentUser ="";

  constructor(private tokenStorageService : TokenStorageService,private router: Router) { }

  ngOnInit(): void {
    this.CurrentUser = this.tokenStorageService.getUser();
  }

  logout(): void {
    this.tokenStorageService.signOut();
    this.router.navigate(['/login']);
    window.location.reload();
  }


}
