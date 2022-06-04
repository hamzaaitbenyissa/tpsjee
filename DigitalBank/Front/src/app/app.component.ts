import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'DigitalBankFront';
  isLoggedIn = false;
  CurrentUser = "";

  constructor(private tokenStorageService: TokenStorageService) {}

  ngOnInit(): void {
    const token = this.tokenStorageService.getToken() ;
    
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
       this.CurrentUser = this.tokenStorageService.getUser();
    }
  }
}
