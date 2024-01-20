package com.microservices.one.models.dto;

import jakarta.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class ClientDto implements Serializable {


    private Long id;
    private String firstName;
    private String secondFirstName;
    private String secondName;
    private String secondLastName;
    @Pattern(regexp = "[CC|P]", message = "  Tipo de documento se deberá reemplazar por  CC : Cedula o PP : pasaporte pues solo permite este tipo de carácteres para este dato en base de datos.")
    private String documentType;
    private String numberDocument;
    private Integer numberPhone;
    private Integer movil;
    private String email;
    private String address;
    private String city;
    private Integer status;

    private static final long serialVersionUID = 5462223600L;
}

