package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.dto.HistoryDTO;
import com.codingduo.cinemabookingticket.model.*;
import com.codingduo.cinemabookingticket.repository.*;
import com.codingduo.cinemabookingticket.service.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

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
    private UserSystemRepository customerRepository;

    @Autowired
    private ComboRepository comboRepository;

    @Autowired
    private ComboHistoryRepository comboHistoryRepository;

    @Override
    public History save(HistoryDTO historyDTO) {
        // Tạo đối tượng History từ HistoryDTO
        History history = new History();
        history.setDateBooking(new Date(System.currentTimeMillis()));
        history.setTotal(historyDTO.getTotal());

        // Lấy đối tượng Customer từ cơ sở dữ liệu bằng ID
        UserSystem customer = customerRepository.findById(historyDTO.getIdCustomer())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Customer với ID " + historyDTO.getIdCustomer()));
        history.setCustomer(customer);

        // Lưu history vào cơ sở dữ liệu
        historyRepository.save(history);

        ShowTime showTime = showTimeRepository.findById(historyDTO.getShowTimeId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy ShowTime với ID " + historyDTO.getShowTimeId()));

        // Tạo các đối tượng HistoryDetail từ seatIds và showTimeId
        List<HistoryDetail> historyDetails = new ArrayList<>();
        List<ComboHistory> comboHistoryList = new ArrayList<>();
        for (Long seatId : historyDTO.getSeatIds()) {
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy Seat với ID " + seatId));

            HistoryDetail historyDetail = new HistoryDetail();
            historyDetail.setHistory(history);
            historyDetail.setSeat(seat);
            historyDetail.setShowTime(showTime);
            historyDetails.add(historyDetail);
        }
        for(HistoryDTO.ComboDetail comboDetail : historyDTO.getComboDetails()) {
            ComboHistory comboHistory = new ComboHistory();
            comboHistory.setHistory(history);
            Combo combo = comboRepository.getReferenceById(comboDetail.getId());
            comboHistory.setCombo(combo);
            comboHistory.setQuantity(comboDetail.getQuantity());
            comboHistoryList.add(comboHistory);
        }

        // Lưu historyDetails vào cơ sở dữ liệu
        historyDetailRepository.saveAll(historyDetails);

        // Lưu ComboHistory vào cơ sở dữ liệu
        comboHistoryRepository.saveAll(comboHistoryList);

        // Thiết lập mối quan hệ giữa history và historyDetails
        history.setHistoryDetailList(historyDetails);

        // Thiết lập mối quan hệ giữa history và combo history
        history.setComboHistoryList(comboHistoryList);
        return historyRepository.save(history);
    }

    @Override
    public List<History> getAllByCustomer(Long id) {
        UserSystem customer = customerRepository.getReferenceById(id);
        return historyRepository.findAllByCustomer(customer);
    }

    @Override
    public Map<Date, Double> calculateDailyRevenueInWeek() {
        LocalDate now = LocalDate.now();
        LocalDate startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        // Lấy dữ liệu từ cơ sở dữ liệu theo tuần hiện tại
        List<History> historiesInCurrentWeek = historyRepository
                .findHistoriesByDateBookingBetween(Date.valueOf(startOfWeek), Date.valueOf(endOfWeek));

        // Tạo Map để lưu trữ tổng tiền theo từng ngày
        Map<Date, Double> dailyRevenueMap = new HashMap<>();
        // Khởi tạo giá trị mặc định là 0 cho tất cả các ngày trong tuần
        for (LocalDate date = startOfWeek; date.isBefore(endOfWeek.plusDays(1)); date = date.plusDays(1)) {
            dailyRevenueMap.put(Date.valueOf(date), 0.0);
        }
        // Duyệt qua danh sách History và tính tổng tiền cho mỗi ngày
        for (History history : historiesInCurrentWeek) {
            Date date = history.getDateBooking();
            double total = history.getTotal();

            // Cập nhật tổng tiền cho ngày tương ứng trong Map
            dailyRevenueMap.merge(date, total, Double::sum);
        }

        return dailyRevenueMap;
    }

    @Override
    public Map<YearMonth, Double> calculateMonthlyRevenue() {
        LocalDate now = LocalDate.now();
        YearMonth startOfYear = YearMonth.of(now.getYear(), Month.JANUARY);
        YearMonth endOfYear = YearMonth.of(now.getYear(), Month.DECEMBER);

        //giá trị mặc định là 0
        Map<YearMonth, Double> monthlyRevenueMap = new LinkedHashMap<>();
        for (YearMonth month = startOfYear; !month.isAfter(endOfYear); month = month.plusMonths(1)) {
            monthlyRevenueMap.put(month, 0.0);

            // Lấy history theo từng tháng
            List<History> historiesInMonth = historyRepository
                    .findHistoriesByDateBookingBetween(
                            Date.valueOf(month.atDay(1)),
                            Date.valueOf(month.atEndOfMonth())
                    );

            // Tính tổng tiền cho tháng hiện tại
            double totalRevenueInMonth = historiesInMonth.stream()
                    .mapToDouble(History::getTotal)
                    .sum();

            // Cập nhật tổng tiền cho tháng tương ứng trong Map
            monthlyRevenueMap.put(month, totalRevenueInMonth);
        }

        return monthlyRevenueMap;
    }
}
