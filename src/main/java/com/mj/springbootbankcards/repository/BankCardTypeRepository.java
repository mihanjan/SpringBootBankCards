package com.mj.springbootbankcards.repository;

import com.mj.springbootbankcards.model.BankCardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankCardTypeRepository extends JpaRepository<BankCardType, Integer> {

    @Query("SELECT b FROM BankCardType b WHERE b.cardType = 'DEBIT'")
    List<BankCardType> findDebitBankCardTypes();

    @Query("SELECT b FROM BankCardType b WHERE b.cardType = 'CREDIT'")
    List<BankCardType> findCreditBankCardTypes();
}
