package com.sanskar.finance_dashboard.controller;

import com.sanskar.finance_dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    public Map<String, Object> summary() {
        return service.getSummary();
    }

    @GetMapping("/insights")
    @PreAuthorize("hasAnyRole('ANALYST','ADMIN')")
    public Map<String, Double> categoryInsights() {
        return service.categoryInsights();
    }
}
