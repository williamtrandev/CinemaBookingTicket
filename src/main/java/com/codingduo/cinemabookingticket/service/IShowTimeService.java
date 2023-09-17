package com.codingduo.cinemabookingticket.service;

import com.codingduo.cinemabookingticket.model.ShowTime;

import java.util.List;

public interface IShowTimeService {
    List<ShowTime> getShowTimeByTheater(Long theater_id);
}
