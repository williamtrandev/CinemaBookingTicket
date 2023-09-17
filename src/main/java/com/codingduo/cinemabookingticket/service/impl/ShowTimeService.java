package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.model.ShowTime;
import com.codingduo.cinemabookingticket.repository.ShowtimeRepository;
import com.codingduo.cinemabookingticket.service.IShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowTimeService implements IShowTimeService {
    @Autowired
    ShowtimeRepository showtimeRepository;
    @Override
    public List<ShowTime> getShowTimeByTheater(Long id) {
        return showtimeRepository.findShowTimesByTheaterId(id);
    }
}
