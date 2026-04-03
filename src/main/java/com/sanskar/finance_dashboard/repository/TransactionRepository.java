package com.sanskar.finance_dashboard.repository;

import com.sanskar.finance_dashboard.entity.Transaction;
import com.sanskar.finance_dashboard.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findByType(Type type, Pageable pageable);
    Page<Transaction> findByCategoryContaining(String category, Pageable pageable);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type='INCOME'")
    Double totalIncome();

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type='EXPENSE'")
    Double totalExpense();

    @Query("SELECT t.category, SUM(t.amount) FROM Transaction t WHERE t.type='EXPENSE' GROUP BY t.category")
    List<Object[]> categoryWiseExpense();

}
