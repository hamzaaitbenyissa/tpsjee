import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { BankAccount } from '../models/Account';
import { Customer } from '../models/Customer';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  constructor(private http: HttpClient) {}

  public searchCustomers(keyword: String): Observable<Array<Customer>> {
    return this.http.get<Array<Customer>>(
      environment.backendServer + '/customers/search/?keyword=' + keyword
    );
  }
  // add a new customer
  public addCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(
      environment.backendServer + '/customers',
      customer
    );
  }

  // delete an user
  public deleteCustomer(id: number) {
    return this.http.delete(environment.backendServer + '/customers/' + id);
  }

  //get bank accounts of costumer
  public getAccountsofCustomer(
    Customerid: number
  ): Observable<Array<BankAccount>> {
    return this.http.get<Array<BankAccount>>(
      environment.backendServer + '/customer/' + Customerid + '/accounts/'
    );
  }
}
