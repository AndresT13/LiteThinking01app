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
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "services")
public class ServicesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "SERVICE_NAME")
    private String servicesName;
    @Column(name = "CODE_SERVICE_NAME")
    private String codeServiceName;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="PRICE")
    private Double price;
    @Column(name="IVA")
    private Double iva;
    @Column(name= "STATUS")
    private Integer status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "services_types_id")
    private ServiceTypeEntity services_types;

    @OneToMany(mappedBy = "services")
    private List<ExpensesEntity> expenses;





}
