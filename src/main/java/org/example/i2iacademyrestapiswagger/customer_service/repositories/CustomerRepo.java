package org.example.i2iacademyrestapiswagger.customer_service.repositories;

import org.example.i2iacademyrestapiswagger.customer_service.entity.CustomerTable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CustomerRepo extends CrudRepository<CustomerTable,String> {
    @Query("SELECT c FROM CustomerTable c WHERE c.email=:email")
    CustomerTable findByEmail(@Param("email") String email);

    @Query("SELECT c FROM CustomerTable c WHERE c.id=:id")
    CustomerTable findCustomerById(@Param("id") String id);

    @Query("SELECT c FROM CustomerTable c")
    List<CustomerTable> findAllCustomers();

    @Modifying
    @Query("UPDATE CustomerTable c SET c.email=:email WHERE c.id=:id")
    int updateEmail(@Param("email") String email ,@Param("id") String id);

     @Modifying
    @Query("DELETE FROM CustomerTable c WHERE c.id=:id")
    void deleteCustomerById(@Param("id") String id);
}
