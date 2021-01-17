package com.mss1569.cartoriofront.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notary {
    private Long id;
    private String name;
    private String address;
}
