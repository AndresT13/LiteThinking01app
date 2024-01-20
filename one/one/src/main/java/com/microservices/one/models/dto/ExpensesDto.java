package com.microservices.one.models.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class ExpensesDto implements Serializable {

    private Long id;
    private Integer amount;
    private String concept;
    private String codeExpenses;
    private Double price;
    private Integer status;

    private static final long serialVersionUID = 5462223600L;
}
