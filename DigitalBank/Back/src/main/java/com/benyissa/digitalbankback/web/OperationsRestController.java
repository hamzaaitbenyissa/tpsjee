package com.benyissa.digitalbankback.web;


import com.benyissa.digitalbankback.DTOs.AccountHistoryDTO;
import com.benyissa.digitalbankback.DTOs.AccountOperationDTO;
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
    public List<AccountOperationDTO> getHistorique(@PathVariable String accountId){
        return operationService.historique(accountId);
    }

    //get all operations of an account as pages
    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getHistoriquePages(
            @PathVariable String accountId,
            @RequestParam(name ="page", defaultValue = "0") int page,
            @RequestParam(name ="size", defaultValue = "5") int size) throws BankAccountNotFoundException {

        return operationService.HistoriquePages(accountId, page, size);
    }

}
