package com.leapwise.bankingsystem.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "amount", nullable = false, unique = true)
    private BigDecimal amount;

    @Column(name = "message", nullable = false, unique = true)
    private String message;

    @Column(name = "timestamp", nullable = false, unique = true)
    private LocalDate timestamp;

    @CreationTimestamp
    private LocalDate created;
    @UpdateTimestamp
    private LocalDate modified;

    private Currency currency;

    //todo: PROVJERI CASCADE TYPE
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="sender_account_id", nullable = false)
    private Account senderAccount;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="receiver_account_id", nullable = false)
    private Account receiverAccount;
}
