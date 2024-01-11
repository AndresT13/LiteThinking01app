package com.microservices.one.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
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
    @Column(name = "DOCUMENT_TYPE", nullable = false, length = 3)
    private String documentType;
    @Column(name = "NUMBER_DOCUMENT", nullable = false, length = 3)
    private String numberDocument;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "BIRTH")
    private Date birth;

    @Column(name = "NUMBER_PHONE", length = 10)
    private Integer numberPhone;
    @Column(name = "MOVIL", length = 12)
    private Integer movil;

    @Column(name = "EMAIL", length = 120)
    private String email;
    @Column(name = "ADDRESS", length = 150)
    private String address;
    @Column(name = "CITY", length = 150)
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