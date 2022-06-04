import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountsComponent } from './components/accounts/accounts.component';
import { AddCustomerComponent } from './components/add-customer/add-customer.component';
import { CustomerAccountsComponent } from './components/customer-accounts/customer-accounts.component';
import { CustomersComponent } from './components/customers/customers.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  { path: '', redirectTo: '/customers', pathMatch: 'full' }, // redirect to home
  { path: 'login', component: LoginComponent },
  { path: 'customers', component: CustomersComponent },
  { path: 'addcustomer', component: AddCustomerComponent },
  { path: 'accounts', component: AccountsComponent },
  { path: 'customer-accounts/:id', component: CustomerAccountsComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
