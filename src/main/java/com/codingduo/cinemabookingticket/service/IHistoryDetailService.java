package com.codingduo.cinemabookingticket.service;

import com.codingduo.cinemabookingticket.model.HistoryDetail;

import java.util.List;

public interface IHistoryDetailService {
    List<HistoryDetail> getAllByShowTimeId(Long showtimeId);
}
