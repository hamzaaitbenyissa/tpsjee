package com.benyissa.digitalbankback.web;


import com.benyissa.digitalbankback.DTOs.*;
import com.benyissa.digitalbankback.exceptions.BalanceNotSufficientException;
import com.benyissa.digitalbankback.exceptions.BankAccountNotFoundException;
import com.benyissa.digitalbankback.services.OperationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class OperationsRestController {

    private OperationService operationService;

    //get all operations of an account
    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistorique(@PathVariable String accountId) {
        return operationService.historique(accountId);
    }

    //get all operations of an account as pages
    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getHistoriquePages(
            @PathVariable String accountId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) throws BankAccountNotFoundException {

        return operationService.HistoriquePages(accountId, page, size);
    }

    //debit an account
    @PostMapping("/accounts/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.operationService.debit(debitDTO.getAccountId(), debitDTO.getAmount(), debitDTO.getDescription());
        return debitDTO;
    }

    //credit an account
    @PostMapping("/accounts/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException {
        this.operationService.credit(creditDTO.getAccountId(), creditDTO.getAmount(), creditDTO.getDescription());
        return creditDTO;
    }

    //transfer an amount from an account to another
    @PostMapping("/accounts/transfer")
    public void transfer(@RequestBody TransferDTO transferDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.operationService.transfer(
                transferDTO.getAccountSource(),
                transferDTO.getAccountDestination(),
                transferDTO.getAmount());
    }


}
