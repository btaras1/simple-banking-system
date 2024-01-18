package com.leapwise.bankingsystem.service;

import com.leapwise.bankingsystem.domain.Account;
import com.leapwise.bankingsystem.domain.Transaction;
import com.leapwise.bankingsystem.repository.TransactionRepository;
import com.leapwise.bankingsystem.rest.dto.TransactionRequestDto;
import com.leapwise.bankingsystem.rest.dto.TransactionResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TransactionService {
    AccountService accountService;
    TransactionRepository repository;

    public UUID create(TransactionRequestDto request) {
        Account sender = accountService.getById(request.getSenderAccountId());
        Account receiver = accountService.getById(request.getReceiverAccountId());

        Currency currency = Currency.getInstance(request.getCurrency());

        var entity = Transaction.builder()
                .amount(request.getAmount())
                .currency(currency)
                .message(request.getMessage())
                .timestamp(request.getTimestamp() != null
                        ? request.getTimestamp()
                        : LocalDate.now())
                .senderAccount(sender)
                .receiverAccount(receiver)
                .build();

        Transaction current = repository.save(entity);

        sender.setBalance(sender.getBalance().subtract(request.getAmount()));
        receiver.setBalance(receiver.getBalance().add(request.getAmount()));

        accountService.save(sender);
        accountService.save(receiver);

        return current.getId();
    }

    public List<TransactionResponseDto> getAllByCustomerId(UUID customerId, String params) {

    }
}
