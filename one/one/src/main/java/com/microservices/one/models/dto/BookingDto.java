package com.microservices.one.models.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
public class BookingDto implements Serializable {

    private Long id;
    private String codCLient;
    private String identificationNumber;
    private Integer roomNumber;
    private Double iva;
    private Integer status;
    private LocalDateTime dateIn;
    private LocalDateTime dateOut;

    private static final long serialVersionUID = 5462223600L;
}
