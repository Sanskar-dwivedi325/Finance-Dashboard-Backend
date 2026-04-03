package com.sanskar.finance_dashboard.service;

import com.sanskar.finance_dashboard.entity.Transaction;
import com.sanskar.finance_dashboard.entity.Type;
import com.sanskar.finance_dashboard.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Transaction create(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Page<Transaction> getAll(int page, int size){

        Pageable pageable= PageRequest.of(page,size, Sort.by("date").descending());
        return transactionRepository.findAll(pageable);
    }

    public Page<Transaction> search(String category, Type type, int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("date").descending());

        if (category != null && type != null) {
            return (Page<Transaction>) transactionRepository.findAll(pageable).map(t->t).filter(t->t.getCategory().toLowerCase().contains(category.toLowerCase())
            && t.getType()==type);
        }

        if (category != null) {
            return transactionRepository.findByCategoryContaining(category, pageable);
        }

        if (type != null) {
            return transactionRepository.findByType(type, pageable);
        }

        return transactionRepository.findAll(pageable);
    }

}
