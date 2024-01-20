package com.microservices.one.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "seassons")
public class SeassonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "CATEGORY")
    private Integer category;
    @Column(name ="PRICE")
    private Double price;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "DATE_IN")
    private LocalDateTime dateIn;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "DATE_OUT")
    private LocalDateTime dateOut;
    @Column(name= "STATUS")
    private Integer status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "seassons")
    private List<RoomPriceEntity> room_prices;
}
