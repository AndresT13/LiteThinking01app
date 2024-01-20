package com.microservices.one.models.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
public class RoomDto {
    private Long id;
    private Integer numberRooms;
    private Integer codeRoom;
    private String category;
    private Integer status;

}
