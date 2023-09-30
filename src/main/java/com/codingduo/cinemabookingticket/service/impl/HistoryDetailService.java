package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.model.HistoryDetail;
import com.codingduo.cinemabookingticket.repository.HistoryDetailRepository;
import com.codingduo.cinemabookingticket.service.IHistoryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryDetailService implements IHistoryDetailService {
    @Autowired
    private HistoryDetailRepository historyDetailRepository;
    @Override
    public List<HistoryDetail> getAllByShowTimeId(Long showtimeId) {
        return historyDetailRepository.findAllByShowTimeId(showtimeId);
    }
}
