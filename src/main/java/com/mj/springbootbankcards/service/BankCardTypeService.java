package com.mj.springbootbankcards.service;

import com.mj.springbootbankcards.model.BankCardType;
import com.mj.springbootbankcards.repository.BankCardTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankCardTypeService {

    private final BankCardTypeRepository bankCardTypeRepository;

    public BankCardTypeService(BankCardTypeRepository bankCardTypeRepository) {
        this.bankCardTypeRepository = bankCardTypeRepository;
    }

    public BankCardType save(BankCardType bankCardType) {
        return bankCardTypeRepository.save(bankCardType);
    }

    public List<BankCardType> findAll() {
        return bankCardTypeRepository.findAll();
    }

    public List<BankCardType> findDebitBankCardTypes() {
        return bankCardTypeRepository.findDebitBankCardTypes();
    }

    public List<BankCardType> findCreditBankCardTypes() {
        return bankCardTypeRepository.findCreditBankCardTypes();
    }

    public Optional<BankCardType> findById(int id) {
        return bankCardTypeRepository.findById(id);
    }

    public void deleteById(int id) {
        bankCardTypeRepository.deleteById(id);
    }
}
