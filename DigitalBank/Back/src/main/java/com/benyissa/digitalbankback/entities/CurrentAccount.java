package com.benyissa.digitalbankback.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@DiscriminatorValue("CA")
public class CurrentAccount extends BankAccount{

    private double overDraft;
}
