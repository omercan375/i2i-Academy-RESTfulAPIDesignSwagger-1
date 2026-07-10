package org.example.i2iacademyrestapiswagger.customer_service.service;

import lombok.RequiredArgsConstructor;
import org.example.i2iacademyrestapiswagger.customer_service.dto.UpdateEmailDto;
import org.example.i2iacademyrestapiswagger.customer_service.exception.CustomerAlreadyExistsException;
import org.example.i2iacademyrestapiswagger.customer_service.exception.CustomerNotFoundException;
import org.example.i2iacademyrestapiswagger.customer_service.exception.EmailAlreadyExistsException;
import org.example.i2iacademyrestapiswagger.customer_service.repositories.CustomerRepo;
import org.example.i2iacademyrestapiswagger.customer_service.entity.CustomerTable;

import org.example.i2iacademyrestapiswagger.customer_service.dto.CreateCustomerDto;
import org.example.i2iacademyrestapiswagger.customer_service.dto.FindCustomer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService implements AbstractCustomerService {

    private final CustomerRepo customerRepo;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void createCustomer(CreateCustomerDto createCustomerDto) {
        // customer daha önceden varmı yokmu kontrolü email ile yapılıyor unıqe olduğu için
        CustomerTable customerExistControl = customerRepo.findByEmail(createCustomerDto.getEmail());
        if (customerExistControl != null) {
            throw new CustomerAlreadyExistsException("Customer already exists");
        }
        // password hash
        String hashed = passwordEncoder.encode(createCustomerDto.getPassword());
        // create uuıd
        String customerId = UUID.randomUUID().toString();
        CustomerTable createCustomer = CustomerTable.builder()
                .id(customerId)
                .firstName(createCustomerDto.getFirstName())
                .lastName(createCustomerDto.getLastName())
                .email(createCustomerDto.getEmail())
                .phone(createCustomerDto.getPhone())
                .password(hashed)
                .build();
        customerRepo.save(createCustomer);
    }

    @Override
    public FindCustomer findCustomer(CustomerTable customer) {
        return FindCustomer.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhone())
                .build();
    }

    @Override
    public FindCustomer findCustomerById(String customerId) {
        // find customer by id
        CustomerTable findCustomer = customerRepo.findCustomerById(customerId);
        if (findCustomer == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        return findCustomer(findCustomer);
    }

    @Override
    public List<FindCustomer> findAllCustomers() {
        List<FindCustomer> findAllCustomerList = new ArrayList<>();

        List<CustomerTable> findCustomers = customerRepo.findAllCustomers();
        for (CustomerTable customer : findCustomers) {
            findAllCustomerList.add(findCustomer(customer));
        }
        return findAllCustomerList;
    }

    @Override
    @Transactional
    public void updateEmail(UpdateEmailDto updateEmailDto) {
        // new email exists controller
        CustomerTable customerExistControl = customerRepo.findByEmail(updateEmailDto.getEmail());
        if (customerExistControl != null) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        // update email
        int updateEmail = customerRepo.updateEmail(updateEmailDto.getEmail(), updateEmailDto.getCustomerId());
        if (updateEmail == 0) {
            throw new CustomerNotFoundException("Customer not found");
        }
    }

    @Override
    @Transactional
    public void deleteCustomer(String customerId) {
        // find customer
        CustomerTable customerExistControl = customerRepo.findCustomerById(customerId);
        if (customerExistControl == null) {
            throw new CustomerNotFoundException("Customer not found");
        }

        customerRepo.deleteCustomerById(customerId);
    }


}
