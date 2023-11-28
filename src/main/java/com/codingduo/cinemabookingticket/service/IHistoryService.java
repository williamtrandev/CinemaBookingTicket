package com.codingduo.cinemabookingticket.service;

import com.codingduo.cinemabookingticket.dto.HistoryDTO;
import com.codingduo.cinemabookingticket.model.History;

import java.sql.Date;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

public interface IHistoryService {
    History save(HistoryDTO historyDTO);
    List<History> getAllByCustomer(Long id);

    Map<Date, Double> calculateDailyRevenueInWeek();

    Map<YearMonth, Double> calculateMonthlyRevenue();
}
