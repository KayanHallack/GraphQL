package com.estudo.dto;

import com.estudo.constants.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {

    private Long id;
    private ZonedDateTime date;
    private Integer quantity;
    private Status status;
    private Long clientId;
    private Long productId;
}
