import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Customer } from '../components/customers/Customer';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  constructor(private http: HttpClient) {}

  public getCustomers(): Observable<Array<Customer>> {
    return this.http.get<Array<Customer>>(
      environment.backendServer + '/customers'
    );
  }

  public searchCustomers(keyword:String): Observable<Array<Customer>> {
    return this.http.get<Array<Customer>>(
      environment.backendServer + "/customers/search/?keyword="+keyword
    );
  }


}
