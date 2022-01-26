package com.mj.springbootbankcards.service;

import com.mj.springbootbankcards.model.BankCardType;
import com.mj.springbootbankcards.repository.BankCardTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankCardTypeService {

    private final BankCardTypeRepository bankCardTypeRepository;

    public BankCardTypeService(BankCardTypeRepository bankCardTypeRepository) {
        this.bankCardTypeRepository = bankCardTypeRepository;
    }

    public BankCardType add(BankCardType bankCardType) {
        return bankCardTypeRepository.save(bankCardType);
    }

    public void update(BankCardType bankCardType, int id) {
        if (bankCardType.isNew()) {
            bankCardType.setId(id);
        } else if (bankCardType.getId() != id) {
            throw new IllegalArgumentException(bankCardType + " must has id=" + id);
        }
        bankCardTypeRepository.save(bankCardType);
    }

    public List<BankCardType> getAll() {
        return bankCardTypeRepository.findAll();
    }

    public List<BankCardType> getDebit() {
        return bankCardTypeRepository.findDebitBankCardTypes();
    }

    public List<BankCardType> getCredit() {
        return bankCardTypeRepository.findCreditBankCardTypes();
    }

    public BankCardType get(int id) {
        return bankCardTypeRepository.findById(id).get();
    }

    public void delete(int id) {
        bankCardTypeRepository.deleteById(id);
    }
}
