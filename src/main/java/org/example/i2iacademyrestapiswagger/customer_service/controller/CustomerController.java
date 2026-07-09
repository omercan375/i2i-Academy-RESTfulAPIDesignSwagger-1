package org.example.i2iacademyrestapiswagger.customer_service.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.i2iacademyrestapiswagger.customer_service.dto.UpdateEmailDto;
import org.example.i2iacademyrestapiswagger.customer_service.service.CustomerService;
import org.example.i2iacademyrestapiswagger.customer_service.dto.CreateCustomerDto;
import org.example.i2iacademyrestapiswagger.customer_service.dto.FindCustomer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/customer-service")
@RequiredArgsConstructor
@Tag(name = "Customer API", description = "Customer CRUD operations")

public class CustomerController implements AbstractCustomerController{
    private final CustomerService customerService;

    @Override
    @PostMapping("/create")
    @Operation(summary = "create new customer")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CreateCustomerDto createCustomerDto) {
        customerService.createCustomer(createCustomerDto);
        return ResponseEntity.ok().build();
    }
    @Override
    @GetMapping("/find")
    @Operation(summary = "find customer by id")
    public ResponseEntity<?> findCustomerById(@RequestParam @NotNull @Valid String id) {
        FindCustomer  customer = customerService.findCustomerById(id);
        return ResponseEntity.ok().body(customer);
    }
    @Override
    @GetMapping("/find-all")
    @Operation(summary = "find all customers")
    public ResponseEntity<?> findAllCustomers() {
        List<FindCustomer> customers = customerService.findAllCustomers();
        return ResponseEntity.ok().body(customers);
    }
    @Override
    @PutMapping("/update-email")
    @Operation(summary = "update customer email")
    public ResponseEntity<?> updateEmail(@Valid @RequestBody UpdateEmailDto updateEmailDto) {
        customerService.updateEmail(updateEmailDto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete-customer")
    @Operation(summary = "delete customer")
    public ResponseEntity<?> deleteCustomer(@RequestParam @NotNull @Valid String id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }


}
