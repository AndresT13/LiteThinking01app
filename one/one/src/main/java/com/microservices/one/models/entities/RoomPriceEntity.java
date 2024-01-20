package com.microservices.one.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "room_prices")
public class RoomPriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CATEGORY")
    private Integer category;
    @Column(name = "SEASSON")
    private String seasson;
    @Column(name = "PRICE")
    private Double price;
    @Column(name= "STATUS")
    private Integer status;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name="seasson_id")
    private SeassonEntity seassons;

    @ManyToOne
    @JoinColumn(name="room_type_id")
    private RoomTypeEntity rooms_type;


}
