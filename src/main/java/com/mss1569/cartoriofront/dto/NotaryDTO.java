package com.mss1569.cartoriofront.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotaryDTO {
    @NotEmpty(message = "Name cannot be empty!")
    private String name;
    @NotEmpty(message = "Address cannot be empty!")
    private String address;
}