package com.benyissa.digitalbankback.repositories;

import com.benyissa.digitalbankback.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
