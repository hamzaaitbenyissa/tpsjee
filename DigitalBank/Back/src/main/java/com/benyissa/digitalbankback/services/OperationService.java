package com.benyissa.digitalbankback.services;


import com.benyissa.digitalbankback.DTOs.AccountHistoryDTO;
import com.benyissa.digitalbankback.DTOs.AccountOperationDTO;
import com.benyissa.digitalbankback.exceptions.BalanceNotSufficientException;
import com.benyissa.digitalbankback.exceptions.BankAccountNotFoundException;

import java.util.List;

public interface OperationService {

    List<AccountOperationDTO> historique(String accountId);

    AccountHistoryDTO HistoriquePages(String accountId, int page, int size) throws BankAccountNotFoundException;


    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;

    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;

    void transfer(String accountIdSource, String accountIdDest, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

}
