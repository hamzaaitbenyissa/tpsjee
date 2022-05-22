import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css'],
})
export class CustomersComponent implements OnInit {
  // customers: Customer[]=[];
  customers: any;
  pages: number[] = [1, 2, 3, 4, 5, 6, 7, 8];

  constructor(private customerService : CustomerService) {}
  ngOnInit(): void {
    this.customerService.getCustomers().subscribe({
      next: (data) => {
        this.customers = data;
        console.log(data);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  myForm = new FormGroup({
    keyword: new FormControl(''),
  });

  get f() {
    return this.myForm.controls;
  }

  // handle delete  event
  onDelete() {
    confirm('Are you sure you want to delete');
  }

  // handle edit event
  onEdit() {
    confirm('Are you sure you want to edit it ');
  }

  //submit search
  submit() {
    this.customerService.searchCustomers(this.myForm.value.keyword).subscribe({
      next: (data) => {
        this.customers = data;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
