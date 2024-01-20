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
@Table(name="expenses")
public class ExpensesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "AMOUNT")
    private Integer amount;
    @Column(name = "CONCEPT")
    private String concept;
    @Column(name= "CODE_EXPENSES")
    private String codeExpenses;
    @Column(name= "PRICE")
    private Double price;
    @Column(name= "STATUS")
    private Integer status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name="service_id")
    private ServicesEntity services;

    @ManyToOne
    @JoinColumn(name="bookings_id")
    private BookingEntity bookings;

}
