package org.example.i2iacademyrestapiswagger.customer_service.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.i2iacademyrestapiswagger.customer_service.dto.CreateCustomerDto;
import org.example.i2iacademyrestapiswagger.customer_service.dto.UpdateEmailDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public interface AbstractCustomerController {
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CreateCustomerDto createCustomerDto);
    public ResponseEntity<?> findCustomerById(@RequestParam @NotNull @Valid String  id);
    public ResponseEntity<?> findAllCustomers();
    public ResponseEntity<?> updateEmail(@Valid @RequestBody UpdateEmailDto updateEmailDto);
    public ResponseEntity<?> deleteCustomer(@RequestParam @NotNull @Valid String id);
}
