package com.microservices.one.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
@Builder
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "SECOND_FIRST_NAME")
    private String secondFirstName;
    @Column(name = "SECOND_NAME", nullable = false)
    private String secondName;
    @Column(name = "SECOND_LAST_NAME")
    private String secondLastName;
    @Pattern(regexp = "[CC|PP]", message = "Tipo de documento se deberá reemplazar por  CC : Cedula o PP : passaporte pues solo permite este tipo de carácteres para este dato en base de datos.")
    @Column(name = "DOCUMENT_TYPE", nullable = false, unique = true)
    private String documentType;
    @Column(name = "NUMBER_DOCUMENT", length = 12)
    private String numberDocument;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "BIRTH")
    private Date birth;
    @Column(name = "NUMBER_PHONE", length = 10)
    private Integer numberPhone;
    @Column(name = "MOVIL", length = 10)
    private Integer movil;
    @Email
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STATUS")
    private Integer status;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    public ClientEntity(Long id, String firstName, String secondName, String secondFirstName, String secondLastName, String documentType, String numberDocument, Integer numberPhone, Integer movil, String email, String address, String city) {
    }

}