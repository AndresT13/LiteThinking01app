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
@Table(name="rooms_type")
public class RoomTypeEntity {

    // crear tabla intermedia entrw Booking y roo type

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "CATEGORY")
    private Integer category;
    @Column(name = "CODE_ROOM")
    private Integer codeRoom;
    @Column(name = "BEDS")
    private Integer beds;
    @Column(name = "BATHROOM")
    private Integer bathroom;
    @Column(name = "LIVING_ROOM")
    private Integer livingRoom;
    @Column(name = "LOUNG")
    private Integer loung;
    @Column(name = "MEZZANINE")
    private Integer mezzanine;
    @Column(name = "TERRACE")
    private Integer terrace;
    @Column(name = "EXTERIORS")
    private Integer exteriors;
    @Column(name= "STATUS")
    private Integer status;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "rooms_type")
    private List<RoomEntity> rooms;

    @OneToMany(mappedBy = "rooms_type")
    private List<RoomPriceEntity> room_prices;







}
