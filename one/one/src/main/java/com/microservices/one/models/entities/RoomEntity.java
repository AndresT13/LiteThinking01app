package com.microservices.one.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "rooms")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name= "ID")
    private Long id;
    @Column(name= "NUMBER_ROOMS")
    private Integer numberRooms;
    @Column(name= "CODE_ROOM")
    private Integer codeRoom;
    @Column(name= "CATEGORY")
    private String category;
    @Column(name= "STATUS")
    private Integer status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "rooms")
    private List<BookingEntity> bookings;

    @ManyToOne
    @JoinColumn(name = "rooms_id")
    private RoomTypeEntity rooms_type;


}
