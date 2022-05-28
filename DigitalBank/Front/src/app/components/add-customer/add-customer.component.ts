import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer.service';
import { Customer } from '../../models/Customer';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css'],
})
export class AddCustomerComponent implements OnInit {
  constructor(private customerService: CustomerService, private router:Router) {}

  ngOnInit(): void {}

  myForm = new FormGroup({
    firstName: new FormControl('', [
      Validators.required,
      Validators.minLength(4),
    ]),
    lastName: new FormControl('', [
      Validators.required,
      Validators.minLength(4),
    ]),
    email: new FormControl('', [Validators.required, Validators.email]),
  });

  get f() {
    return this.myForm.controls;
  }

  submit() {
    console.log(this.myForm.value);

    let customer:Customer=this.myForm.value;
    this.customerService.addCustomer(customer).subscribe({
      next : data=>{
        alert("Customer added successfully");
        this.myForm.reset();
        this.router.navigateByUrl("/customers");
      },
      error : err => {
        console.log(err);
      }
    }
    )
  }
}
