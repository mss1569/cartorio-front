package com.mss1569.cartoriofront.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificateDTO {
    @NotEmpty(message = "Name cannot be empty!")
    private String name;
}
