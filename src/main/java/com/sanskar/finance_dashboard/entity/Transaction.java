package com.sanskar.finance_dashboard.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private Double amount;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String category;

    private LocalDate date;

    private String description;

    @ManyToOne
    private User user;
}
