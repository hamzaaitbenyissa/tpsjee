package com.benyissa.digitalbankback.services;

import com.benyissa.digitalbankback.DTOs.BankAccountDTO;
import com.benyissa.digitalbankback.entities.BankAccount;
import com.benyissa.digitalbankback.exceptions.BankAccountNotFoundException;

import java.util.List;


public interface BankAccountService {

    /*Bank Account  methods */
    BankAccount saveCurrentBankAccount(double initialBalance, Long customerId, double overDraft);

    BankAccount saveSavingBankAccount(double initialBalance, Long customerId, double interestRate);

    List<BankAccountDTO> listBankAccounts();

    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;

}
