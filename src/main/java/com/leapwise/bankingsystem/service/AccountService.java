package com.leapwise.bankingsystem.service;

import com.leapwise.bankingsystem.domain.Account;
import com.leapwise.bankingsystem.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {
    AccountRepository repository;
    public Account getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Account with id = %s not found!", id)));
    }

    public void save(Account entity) {
        repository.save(entity);
    }
}
