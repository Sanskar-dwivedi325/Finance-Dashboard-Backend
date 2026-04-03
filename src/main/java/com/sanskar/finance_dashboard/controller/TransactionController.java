package com.sanskar.finance_dashboard.controller;

import com.sanskar.finance_dashboard.entity.Transaction;
import com.sanskar.finance_dashboard.entity.Type;
import com.sanskar.finance_dashboard.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.create(transaction);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    public Page<Transaction> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Type type
    ) {
        return transactionService.search(category, type, page, size);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Transaction update(@PathVariable Long id, @RequestBody Transaction updated) {

        Transaction t = transactionService.findTransaction(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        t.setAmount(updated.getAmount());
        t.setType(updated.getType());
        t.setCategory(updated.getCategory());
        t.setDate(updated.getDate());
        t.setDescription(updated.getDescription());

        return transactionService.update(t);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {

        transactionService.deleteTransaction(id);
        return "Transaction deleted successfully";
    }




}
