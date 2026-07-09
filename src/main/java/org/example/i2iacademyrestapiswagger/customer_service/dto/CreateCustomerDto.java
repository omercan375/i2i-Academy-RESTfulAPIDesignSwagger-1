package org.example.i2iacademyrestapiswagger.customer_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCustomerDto {
    @NotBlank
    @Size(min = 2,max = 100)
    private String firstName;
    @NotBlank
    @Size(min = 2,max = 100)
    private String lastName;
    @Email
    @Size(min = 2,max = 150)
    private String email;
    @NotBlank
    @Size(min = 11,max = 11)
    private String phone;
    @NotBlank
    @Size(min = 3,max = 200)
    private String password;



}
