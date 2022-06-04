package com.benyissa.digitalbankback.web;

import com.benyissa.digitalbankback.DTOs.BankAccountDTO;
import com.benyissa.digitalbankback.DTOs.CustomerDTO;
import com.benyissa.digitalbankback.services.BankAccountService;
import com.benyissa.digitalbankback.services.CostumerService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@Slf4j
@AllArgsConstructor
@CrossOrigin("*")
public class CustomerRestController {

    private CostumerService costumerService;
    private BankAccountService bankAccountService;


    //    return all costumers
    @GetMapping("/customers")
    public List<CustomerDTO> customers() {
        return costumerService.listCustomers();
    }

    @GetMapping("/customers/search")
    public List<CustomerDTO> searchCustomers(
            @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return costumerService.searchCustomers("" + keyword);
    }


    //    return a specific costumer using its id
    @GetMapping("/customers/{id}")
    public CustomerDTO getcustomer(@PathVariable(name = "id") Long costumerid) {
        return costumerService.getCustomer(costumerid);
    }

    //    add a new costumer
    @PostMapping("/customers")
    public CustomerDTO addcustomer(@RequestBody CustomerDTO customerDTO) {
        return costumerService.saveCustomer(customerDTO);
    }

    //    delete a costumer
    @DeleteMapping("/customers/{id}")
    public void deletecustomer(@PathVariable Long id) {
        costumerService.deleteCustomer(id);
    }

    //    update a costumer
    @PutMapping("/customers/{id}")
    public void deletecustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        customerDTO.setId(id);
        costumerService.updateCustomer(customerDTO);

    }

    //get bank accounts of costumer
    @GetMapping("/customer/{id}/accounts")
    public List<BankAccountDTO> banksOfCustomer(@PathVariable(name = "id") Long customerid) {
        return bankAccountService.listBankAccountsOfCustomer(customerid);
    }


}
