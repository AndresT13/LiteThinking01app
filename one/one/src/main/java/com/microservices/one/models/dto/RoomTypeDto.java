package com.microservices.one.models.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class RoomTypeDto implements Serializable {

    private Long id;
    private Integer category;
    private Integer codeRoom;
    private Integer beds;
    private Integer bathroom;
    private Integer livingRoom;
    private Integer loung;
    private Integer mezzanine;
    private Integer terrace;
    private Integer exteriors;
    private Integer status;

    private static final long serialVersionUID = 5462223600L;
}
