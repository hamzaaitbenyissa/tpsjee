package com.benyissa.digitalbankback.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@DiscriminatorValue("SA")
public class SavingAccount extends BankAccount{

    private double interestRate;
}
