package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.dto.HistoryDTO;
import com.codingduo.cinemabookingticket.model.*;
import com.codingduo.cinemabookingticket.repository.*;
import com.codingduo.cinemabookingticket.service.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService implements IHistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private HistoryDetailRepository historyDetailRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowtimeRepository showTimeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public History save(HistoryDTO historyDTO) {
        // Tạo đối tượng History từ HistoryDTO
        History history = new History();
        history.setDateBooking(new Date(System.currentTimeMillis()));
        history.setTotal(historyDTO.getTotal());

        // Lấy đối tượng Customer từ cơ sở dữ liệu bằng ID
        Customer customer = customerRepository.findById(historyDTO.getIdCustomer())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Customer với ID " + historyDTO.getIdCustomer()));
        history.setCustomer(customer);

        // Lưu history vào cơ sở dữ liệu
        historyRepository.save(history);

        ShowTime showTime = showTimeRepository.findById(historyDTO.getShowTimeId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy ShowTime với ID " + historyDTO.getShowTimeId()));

        // Tạo các đối tượng HistoryDetail từ seatIds và showTimeId
        List<HistoryDetail> historyDetails = new ArrayList<>();
        for (Long seatId : historyDTO.getSeatIds()) {
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy Seat với ID " + seatId));



            HistoryDetail historyDetail = new HistoryDetail();
            historyDetail.setHistory(history);
            historyDetail.setSeat(seat);
            historyDetail.setShowTime(showTime);
            historyDetails.add(historyDetail);
        }

        // Lưu historyDetails vào cơ sở dữ liệu
        historyDetailRepository.saveAll(historyDetails);

        // Thiết lập mối quan hệ giữa history và historyDetails
        history.setHistoryDetailList(historyDetails);
        return historyRepository.save(history);
    }

    @Override
    public List<History> getAllByCustomer(Long id) {
        Customer customer = customerRepository.getReferenceById(id);
        return historyRepository.findAllByCustomer(customer);
    }

}
