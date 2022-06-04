import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { catchError, map, Observable, throwError } from 'rxjs';
import { CustomerService } from 'src/app/services/customer.service';
import { Customer } from '../../models/Customer';
import Swal from 'sweetalert2';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css'],
})
export class CustomersComponent implements OnInit {
  customers!: Observable<Array<Customer>>;
  errorMessage: string = '';

  pages: number[] = [1, 2];

  constructor(
    private customerService: CustomerService,
    private tokenStorage: TokenStorageService,
    private router: Router
  ) {}
  ngOnInit(): void {
    if (!this.tokenStorage.getToken()) {
      this.router.navigate(['/login']);
    } else {
      setTimeout(() => {
        this.handleSearchCustomers();
      }, 500);
    }
  }

  myForm = new FormGroup({
    keyword: new FormControl(''),
  });

  get f() {
    return this.myForm.controls;
  }

  //handle searching event
  handleSearchCustomers() {
    this.customers = this.customerService
      .searchCustomers(this.myForm.value.keyword)
      .pipe(
        catchError((err) => {
          this.errorMessage = err.message;
          return throwError(() => err);
        })
      );
  }

  // handle delete  event
  onDeleteCustomer(c: Customer) {
    Swal.fire({
      title: 'Are you sure?',
      text: 'You will not be able to recover this customer!',
      icon: 'question',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#330020',

      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, keep it',
    }).then((result) => {
      if (result.isConfirmed) {
        this.customerService.deleteCustomer(c.id).subscribe({
          next: (resp) => {
            this.customers = this.customers.pipe(
              map((data) => {
                let index = data.indexOf(c);
                data.slice(index, 1);
                return data;
              })
            );
          },
          error: (err) => {
            console.log(err);
          },
        });
      } else if (result.isDismissed) {
      }
    });
  }

  // handle edit event
  onEditCustomer() {
    confirm('Are you sure you want to edit it ');
  }

  // check the costumer accounts 
  checkCustomerAccounts(customer: Customer) {
    console.log("clicked ")
    this.router.navigateByUrl("/customer-accounts/"+customer.id);
  }

}
