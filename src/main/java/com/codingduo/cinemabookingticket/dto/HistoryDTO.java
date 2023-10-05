package com.codingduo.cinemabookingticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDTO {
    private Long id;
    private Date dateBooking;
    private double total;
    private Long idCustomer;
    private List<Long> seatIds;
    private Long showTimeId;
    // Để lấy danh sách các ghế trả về view của mail
    private String infoSeats;
    // Để lấy danh sách các combo đã chọn trả về view của mail
    private String infoCombo;
}
