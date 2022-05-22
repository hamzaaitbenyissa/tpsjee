package com.benyissa.digitalbankback.web;


import com.benyissa.digitalbankback.DTOs.BankAccountDTO;
import com.benyissa.digitalbankback.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")

public class BankAccountRestController {

    private BankAccountService bankAccountService;

    @GetMapping("/accounts")
    public List<BankAccountDTO> banks() {
        return bankAccountService.listBankAccounts();
    }

    //    return a specific costumer using its id
    @GetMapping("/accounts/{id}")
    public BankAccountDTO getbankaccount(@PathVariable(name = "id") String  accountid) {
        return bankAccountService.getBankAccount(accountid);
    }

}
