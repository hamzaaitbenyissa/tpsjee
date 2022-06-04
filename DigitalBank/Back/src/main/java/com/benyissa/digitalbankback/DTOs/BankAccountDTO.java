package com.benyissa.digitalbankback.DTOs;

import com.benyissa.digitalbankback.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class BankAccountDTO {
    private String type;
    private String id;
    private Double balance;
    private Date CreatedAt;
    private String currency;
    private AccountStatus status;
    private CustomerDTO customerDTO;
}
