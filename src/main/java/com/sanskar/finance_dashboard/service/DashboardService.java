package com.sanskar.finance_dashboard.service;

import com.sanskar.finance_dashboard.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final TransactionRepository repo;
    public Map<String, Object> getSummary() {

        Double income = repo.totalIncome();
        Double expense = repo.totalExpense();

        Map<String, Object> map = new HashMap<>();

        map.put("totalIncome", income != null ? income : 0);
        map.put("totalExpense", expense != null ? expense : 0);
        map.put("netBalance", (income != null ? income : 0) - (expense != null ? expense : 0));

        return map;
    }

    public Map<String, Double> categoryInsights() {

        List<Object[]> data = repo.categoryWiseExpense();

        Map<String, Double> map = new HashMap<>();

        for (Object[] obj : data) {
            map.put((String) obj[0], (Double) obj[1]);
        }

        return map;
    }
}
