package com.microservices.one.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "countries")
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "CODE_COUNTRY")
    private Integer codeCountry;
    @Column(name = "NAME_COUNTRY")
    private String nameCountry;
    @Column(name = "STATUS")
    private Integer status;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "countries", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientEntity> clients;




}

