import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { CustomersComponent } from './components/customers/customers.component';
import { AccountsComponent } from './components/accounts/accounts.component';
import { AddCustomerComponent } from './components/add-customer/add-customer.component';
import { LoginComponent } from './components/login/login.component';
import { authInterceptorProviders } from './helpers/auth.interceptor';
import { CustomerAccountsComponent } from './components/customer-accounts/customer-accounts.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    CustomersComponent,
    AccountsComponent,
    AddCustomerComponent,
    LoginComponent,
    CustomerAccountsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
