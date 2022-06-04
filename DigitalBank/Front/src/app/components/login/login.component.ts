import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import jwt_decode from 'jwt-decode';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  form: any = {
    username: null,
    password: null,
  };
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  constructor(
    private authService: AuthService,
    private tokenStorage: TokenStorageService,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.router.navigate(['/customers']);
    }

  }

  onSubmit(): void {
    const { username, password } = this.form;
    this.authService.login(username, password).subscribe({
      next: (data) => {
        console.log(data);
        const token = data.accessToken;
        var decoded: any = jwt_decode(token);
        this.tokenStorage.saveToken(token);
        this.tokenStorage.saveUser(decoded['sub']);
        this.isLoginFailed = false;
        this.roles = this.tokenStorage.getUser().roles;
        this.router.navigate(['/home']);
        window.location.reload();
      },
      error: (err) => {
        this.errorMessage = err.errorMessage;
        this.isLoginFailed = true;
      },
    });
  }
}
