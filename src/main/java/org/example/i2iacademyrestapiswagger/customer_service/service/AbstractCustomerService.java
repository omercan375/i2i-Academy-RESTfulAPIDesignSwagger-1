package org.example.i2iacademyrestapiswagger.customer_service.service;

import org.example.i2iacademyrestapiswagger.customer_service.dto.CreateCustomerDto;
import org.example.i2iacademyrestapiswagger.customer_service.dto.FindCustomer;
import org.example.i2iacademyrestapiswagger.customer_service.dto.UpdateEmailDto;
import org.example.i2iacademyrestapiswagger.customer_service.entity.CustomerTable;

import java.util.List;
import java.util.UUID;

public interface AbstractCustomerService {
    public void createCustomer(CreateCustomerDto createCustomerDto);
    public FindCustomer findCustomer(CustomerTable customer);
    public FindCustomer findCustomerById(String customerId);
    public List<FindCustomer> findAllCustomers();
    public void updateEmail(UpdateEmailDto updateEmailDto);
    public void deleteCustomer(String customerId);

}
