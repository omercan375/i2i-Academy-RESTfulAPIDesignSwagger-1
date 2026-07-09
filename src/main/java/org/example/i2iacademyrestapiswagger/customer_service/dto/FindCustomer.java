package org.example.i2iacademyrestapiswagger.customer_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindCustomer {
private String firstName;
private String lastName;
private String email;
private String phoneNumber;
}
