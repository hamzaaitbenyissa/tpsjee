package com.benyissa.digitalbankback.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class SavingAccountDTO extends BankAccountDTO {

    private double interestRate;
}
