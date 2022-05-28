package com.benyissa.digitalbankback.services;

import com.benyissa.digitalbankback.DTOs.AccountHistoryDTO;
import com.benyissa.digitalbankback.DTOs.AccountOperationDTO;
import com.benyissa.digitalbankback.entities.AccountOperation;
import com.benyissa.digitalbankback.entities.BankAccount;
import com.benyissa.digitalbankback.enums.OperationType;
import com.benyissa.digitalbankback.exceptions.BalanceNotSufficientException;
import com.benyissa.digitalbankback.exceptions.BankAccountNotFoundException;
import com.benyissa.digitalbankback.mappers.BankAccountMapperImpl;
import com.benyissa.digitalbankback.repositories.AccountOperationRepository;
import com.benyissa.digitalbankback.repositories.BankAccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

;import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
/* Slf4j and log4j are used for logging configuration,*/
@Slf4j
public class OperationServiceImpl implements OperationService {

    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;
    private BankAccountService bankAccountService;
    private BankAccountMapperImpl bankAccountMapper;

    //historique of an account
    @Override
    public List<AccountOperationDTO> historique(String accountId) {
        List<AccountOperation> accountOperations = accountOperationRepository.findByBankAccountId(accountId);
        return accountOperations.stream()
                .map(op -> bankAccountMapper.fromAccountOperation(op))
                .collect(Collectors.toList());
    }

    //historique of an account as pages for a very active account
    @Override
    public AccountHistoryDTO HistoriquePages(String accountId, int page, int size) throws BankAccountNotFoundException {

        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElse(null);
        if (bankAccount == null) {
            throw new BankAccountNotFoundException("Bank Account Not Found");
        }

        Page<AccountOperation> accountOperations = accountOperationRepository.findByBankAccountIdOrderByDateDesc(accountId, PageRequest.of(page, size));
        AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
        List<AccountOperationDTO> accountOperationDTOS = accountOperations.getContent().stream().map(op -> bankAccountMapper.fromAccountOperation(op)).collect(Collectors.toList());
        accountHistoryDTO.setAccountOperationDTOS(accountOperationDTOS);
        accountHistoryDTO.setAccountId(bankAccount.getId());
        accountHistoryDTO.setBalance(bankAccount.getBalance());
        accountHistoryDTO.setPageSize(size);
        accountHistoryDTO.setCurrentPage(page);
        accountHistoryDTO.setTotalPages(accountOperations.getTotalPages());
        return accountHistoryDTO;
    }


    //debit operation
    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
        BankAccount bankAccount=bankAccountRepository.findById(accountId)
                .orElseThrow(()->new BankAccountNotFoundException("BankAccount not found"));

        if (bankAccount.getBalance() < amount) throw new BalanceNotSufficientException("Balance not sufficient");
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        bankAccountRepository.save(bankAccount);
    }

    //credit operation
    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount=bankAccountRepository.findById(accountId)
                .orElseThrow(()->new BankAccountNotFoundException("BankAccount not found"));
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);
    }

    //transfer an amount to another bankaccount
    @Override
    public void transfer(String accountIdSource, String accountIdDest, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
        debit(accountIdSource, amount, "Transfer to " + accountIdDest);
        credit(accountIdDest, amount, "Transfer from " + accountIdSource);
    }





}
