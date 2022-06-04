package com.benyissa.digitalbankback.services;

import com.benyissa.digitalbankback.DTOs.BankAccountDTO;
import com.benyissa.digitalbankback.entities.*;

import com.benyissa.digitalbankback.exceptions.BankAccountNotFoundException;
import com.benyissa.digitalbankback.exceptions.CustomerNotFoundException;
import com.benyissa.digitalbankback.mappers.BankAccountMapperImpl;
import com.benyissa.digitalbankback.repositories.BankAccountRepository;
import com.benyissa.digitalbankback.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
/* Slf4j and log4j are used for logging configuration,*/
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {

    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private BankAccountMapperImpl bankAccountMapper;

    //get all BankAccounts
    @Override
    public List<BankAccountDTO> listBankAccounts() {
        return bankAccountRepository.findAll().stream().map(bankAccount -> {
            if (bankAccount instanceof SavingAccount) {
                SavingAccount savingAccount = (SavingAccount) bankAccount;
                return bankAccountMapper.fromSavingAccount(savingAccount);
            } else {
                CurrentAccount currentAccount = (CurrentAccount) bankAccount;
                return bankAccountMapper.fromCurrentAccount(currentAccount);
            }
        }).collect(Collectors.toList());
    }

    //get bank acoounts of a costumer
    @Override
    public List<BankAccountDTO> listBankAccountsOfCustomer(Long id) {
        return bankAccountRepository.getBankAccountByCustomer_Id(id).stream().map(bankAccount -> {
            if (bankAccount instanceof SavingAccount) {
                SavingAccount savingAccount = (SavingAccount) bankAccount;
                return bankAccountMapper.fromSavingAccount(savingAccount);
            } else {
                CurrentAccount currentAccount = (CurrentAccount) bankAccount;
                return bankAccountMapper.fromCurrentAccount(currentAccount);
            }
        }).collect(Collectors.toList());
    }

    //return a BankAccount using id
    @Override
    public BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("BankAccount not found"));

        if (bankAccount instanceof SavingAccount) {
            SavingAccount savingAccount = (SavingAccount) bankAccount;
            return bankAccountMapper.fromSavingAccount(savingAccount);
        } else {
            CurrentAccount currentAccount = (CurrentAccount) bankAccount;
            return bankAccountMapper.fromCurrentAccount(currentAccount);
        }
    }

    //add  CurrentBankAccount
    @Override
    public BankAccount saveCurrentBankAccount(double initialBalance, Long customerId, double overDraft) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) throw new CustomerNotFoundException("Customer not found");

        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setCustomer(customer);
        currentAccount.setOverDraft(overDraft);
        return bankAccountRepository.save(currentAccount);
    }

    @Override
    public BankAccount saveSavingBankAccount(double initialBalance, Long customerId, double interestRate) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) throw new CustomerNotFoundException("Customer not found");

        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setCustomer(customer);
        savingAccount.setInterestRate(interestRate);
        return bankAccountRepository.save(savingAccount);
    }


}
