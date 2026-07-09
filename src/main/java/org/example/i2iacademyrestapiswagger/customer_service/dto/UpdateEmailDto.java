package org.example.i2iacademyrestapiswagger.customer_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEmailDto {

    @NonNull
    private String customerId;
    @Email
    @Size(min = 5,max = 150)
    private String email;

}
