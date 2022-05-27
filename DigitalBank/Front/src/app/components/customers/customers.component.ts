import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { catchError, map, Observable, throwError } from 'rxjs';
import { CustomerService } from 'src/app/services/customer.service';
import { Customer } from './Customer';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css'],
})
export class CustomersComponent implements OnInit {
  customers!: Observable<Array<Customer>>;
  errorMessage: string = '';

  pages: number[] = [];

  constructor(private customerService: CustomerService) {}
  ngOnInit(): void {
    setTimeout(() => {
    this.handleSearchCustomers();
      
    }, 500);
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
    let conf = confirm('Are you sure want to delete it?');
    if (!conf) return;
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
  }

  // handle edit event
  onEditCustomer() {
    confirm('Are you sure you want to edit it ');
  }
}
