package com.codingduo.cinemabookingticket.service;

import com.codingduo.cinemabookingticket.dto.HistoryDTO;
import com.codingduo.cinemabookingticket.model.History;

import java.util.List;

public interface IHistoryService {
    History save(HistoryDTO historyDTO);
}
