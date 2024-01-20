package com.microservices.one.models.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class ServiceDto implements Serializable {

    private Long id;
    private String servicesName;
    private String codeServiceName;
    private String description;
    private Double price;
    private Double iva;
    private Integer status;

    private static final long serialVersionUID = 5462223600L;
}
