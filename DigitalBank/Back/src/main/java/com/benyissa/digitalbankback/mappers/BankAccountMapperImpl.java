package com.benyissa.digitalbankback.mappers;

import com.benyissa.digitalbankback.DTOs.*;
import com.benyissa.digitalbankback.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/* using service annotation means that we are working on a business logic
 and it's useful when annotation-based configuration and classpath scanning is used */
@Service
public class BankAccountMapperImpl {
    /* in this project we will use just BeanUtils, but we can make it easy with mapstruct framework */

//    CustomerDTO
    public CustomerDTO fromCustomer(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    //BankAccountDTO
    public BankAccountDTO fromBankAccount(BankAccount bankaccount) {
        BankAccountDTO bankaccountDTO = new BankAccountDTO();
        BeanUtils.copyProperties(bankaccount, bankaccountDTO);
        bankaccountDTO.setCustomerDTO(fromCustomer(bankaccount.getCustomer()));
        return bankaccountDTO;
    }

    public BankAccount fromBankAccountDTO(BankAccountDTO bankaccountDTO) {
        BankAccount bankaccount = new BankAccount();
        BeanUtils.copyProperties(bankaccountDTO, bankaccount);
        return bankaccount;
    }


    //CurrentAccountDTO
    public CurrentAccountDTO fromCurrentAccount(CurrentAccount currentaccount) {
        CurrentAccountDTO currentaccountDTO = new CurrentAccountDTO();
        BeanUtils.copyProperties(currentaccount, currentaccountDTO);
        currentaccountDTO.setCustomerDTO(fromCustomer(currentaccount.getCustomer()));
        currentaccountDTO.setType("CurrentAccount");
        return currentaccountDTO;
    }

    public CurrentAccount fromCurrentAccountDTO(CurrentAccountDTO currentaccountDTO) {
        CurrentAccount currentaccount = new CurrentAccount();
        BeanUtils.copyProperties(currentaccountDTO, currentaccount);
        return currentaccount;
    }

    //SavingAccountDTO
    public SavingAccountDTO fromSavingAccount(SavingAccount savingaccount) {
        SavingAccountDTO savingaccountDTO = new SavingAccountDTO();
        BeanUtils.copyProperties(savingaccount, savingaccountDTO);
        savingaccountDTO.setCustomerDTO(fromCustomer(savingaccount.getCustomer()));
        savingaccountDTO.setType("SavingAccount");
        return savingaccountDTO;
    }

    public SavingAccount fromSavingAccountDTO(SavingAccountDTO savingaccountDTO) {
        SavingAccount savingaccount = new SavingAccount();
        BeanUtils.copyProperties(savingaccountDTO, savingaccount);
        return savingaccount;
    }


    //AccountOperationDTO
    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation) {
        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation, accountOperationDTO);
        return accountOperationDTO;
    }






}
