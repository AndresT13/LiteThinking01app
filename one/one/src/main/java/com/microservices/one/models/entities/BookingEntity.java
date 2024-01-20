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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "bookings")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "CODE_CLIENT")
    private String codCLient;
    @Column(name = "IDENTIFICATION_NUMBER")
    private String identificationNumber;
    @Column(name = "ROOM_NUMBER")
    private Integer roomNumber;
    @Column(name = "IVA")
    private Double iva;
    @Column(name = "STATUS")
    private Integer status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "DATE_IN")
    private LocalDateTime dateIn;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "DATE_OUT")
    private LocalDateTime dateOut;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity clients;


    @OneToMany(mappedBy = "bookings")
    private List<ExpensesEntity> expenses;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity rooms;







}
