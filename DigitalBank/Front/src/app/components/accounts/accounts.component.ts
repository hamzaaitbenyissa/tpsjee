import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';
import { AccountDetails, BankAccount } from 'src/app/models/Account';
import { AccountService } from 'src/app/services/account.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css'],
})
export class AccountsComponent implements OnInit {
  accountFormGroup!: FormGroup;
  currentPage: number = 0;
  pageSize: number = 5;
  accountObservable!: Observable<AccountDetails>;
  operationFromGroup!: FormGroup;
  errorMessage!: string;
  account!: BankAccount;

  constructor(
    private fb: FormBuilder,
    private accountService: AccountService,
    private tokenStorage: TokenStorageService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.account = this.router.getCurrentNavigation()?.extras
      .state as BankAccount;
  }

  ngOnInit(): void {
    if (!this.tokenStorage.getToken()) {
      this.router.navigate(['/login']);
    } else {
      this.accountFormGroup = this.fb.group({
        accountId: this.fb.control(this.account.id ? this.account.id : ''),
      });
      this.operationFromGroup = this.fb.group({
        operationType: this.fb.control(null),
        amount: this.fb.control(0),
        description: this.fb.control(null),
        accountDestination: this.fb.control(null),
      });
    }
  }

  handleSearchAccount() {
    let accountId: string = this.accountFormGroup.value.accountId;
    this.accountObservable = this.accountService
      .getAccount(accountId, this.currentPage, this.pageSize)
      .pipe(
        catchError((err) => {
          this.errorMessage = err.message;
          return throwError(() => err);
        })
      );
  }

  gotoPage(page: number) {
    this.currentPage = page;
    this.handleSearchAccount();
  }

  handleAccountOperation() {
    let accountId: string = this.accountFormGroup.value.accountId;
    let operationType = this.operationFromGroup.value.operationType;
    let amount: number = this.operationFromGroup.value.amount;
    let description: string = this.operationFromGroup.value.description;
    let accountDestination: string =
      this.operationFromGroup.value.accountDestination;
    if (operationType == 'DEBIT') {
      this.accountService.debit(accountId, amount, description).subscribe({
        next: (data) => {
          Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'The Operation has been successful',
            showConfirmButton: false,
            timer: 1500,
          });
          this.operationFromGroup.reset();
          this.handleSearchAccount();
        },
        error: (err) => {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Something went wrong!',
          });
          console.log(err);
        },
      });
    } else if (operationType == 'CREDIT') {
      this.accountService.credit(accountId, amount, description).subscribe({
        next: (data) => {
          Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'The Operation has been successful',
            showConfirmButton: false,
            timer: 1500,
          });
          this.operationFromGroup.reset();
          this.handleSearchAccount();
        },
        error: (err) => {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Something went wrong!',
          });
          console.log(err);
        },
      });
    } else if (operationType == 'TRANSFER') {
      this.accountService
        .transfer(accountId, accountDestination, amount, description)
        .subscribe({
          next: (data) => {
            Swal.fire({
              position: 'center',
              icon: 'success',
              title: 'The Operation has been successful',
              showConfirmButton: false,
              timer: 1500,
            });
            this.operationFromGroup.reset();
            this.handleSearchAccount();
          },
          error: (err) => {
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'Something went wrong!',
            });
            console.log(err);
          },
        });
    }
  }
}
