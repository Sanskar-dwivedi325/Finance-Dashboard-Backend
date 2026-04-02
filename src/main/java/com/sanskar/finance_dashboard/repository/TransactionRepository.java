package com.sanskar.finance_dashboard.repository;

import com.sanskar.finance_dashboard.entity.Transaction;
import com.sanskar.finance_dashboard.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findByType(Type type, Pageable pageable);
    Page<Transaction> findByCategoryContaining(String category, Pageable pageable);
}
