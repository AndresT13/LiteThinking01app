package com.microservices.one.models.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ClientDto implements Serializable {


    private String firstName;
    private String secondFirstName;
    private String secondName;
    private String secondLastName;
    private String documentType;
    private String numberDocument;
    private Integer numberPhone;
    private Integer movil;
    private String email;
    private String address;
    private String city;
    private Integer status;

    private static final long serialVersionUID = 1L;
}

