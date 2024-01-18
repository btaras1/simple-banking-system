package com.leapwise.bankingsystem.service;

import com.leapwise.bankingsystem.domain.Customer;
import com.leapwise.bankingsystem.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CustomerService {

    CustomerRepository repository;

    public Customer getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Customer with id = %s not found!", id)));
    }
}
