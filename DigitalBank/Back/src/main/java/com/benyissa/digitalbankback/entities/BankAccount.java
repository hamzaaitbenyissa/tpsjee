package com.benyissa.digitalbankback.entities;

import com.benyissa.digitalbankback.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor


/* Inheritance strategy  is single_Table in default */
@DiscriminatorColumn(name = "TYPE",length = 4)
public class BankAccount {

    @Id
    private String id;
    private Double balance;
    private Date CreatedAt;
    private String currency;
    private AccountStatus status;

    @ManyToOne
/*    It is not necessary to have @JoinColumn annotation.
    You can always override it. If you won't provide it in your
    code then Hibernate will automatically generate one for you
    (default name of column)*/

    private Customer customer;
    @OneToMany(mappedBy = "bankAccount")
    private List<AccountOperation> accountOperations;

}
