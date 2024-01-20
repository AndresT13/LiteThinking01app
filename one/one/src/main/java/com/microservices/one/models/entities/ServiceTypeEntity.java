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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="services_types")
public class ServiceTypeEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CODE_SERVICES_TYPE")
    private String codServiceType;
    @Column(name = "NAME_SERVICES_TYPE")
    private String nameServices;
    @Column(name= "STATUS")
    private Integer status;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @OneToMany(mappedBy= "services_types")
    private List<ServicesEntity> services;

}
