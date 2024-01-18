package com.leapwise.bankingsystem.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "account_number", length = 50, nullable = false, unique = true)
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", length = 50, nullable = false)
    private AccountType accountType;
    @Column(name = "balance", nullable = false, unique = true)
    private BigDecimal balance;
    @Column(name = "past_month_turnover", nullable = false, unique = true)
    private BigDecimal pastMonthTurnover;

    @CreationTimestamp
    private LocalDate created;
    @UpdateTimestamp
    private LocalDate modified;
    //todo: PROVJERI CASCADE TYPE
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="customer_id", nullable = false)
    private Customer customer;
}
