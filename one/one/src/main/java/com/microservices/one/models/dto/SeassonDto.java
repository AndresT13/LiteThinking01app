package com.microservices.one.models.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
public class SeassonDto implements Serializable {

    private Long id;
    private Integer category;
    private Double price;
    private LocalDateTime dateIn;
    private LocalDateTime dateOut;
    private Integer status;

    private static final long serialVersionUID = 5462223600L;
}
