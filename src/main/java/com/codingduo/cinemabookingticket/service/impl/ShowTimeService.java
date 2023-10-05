package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.dto.ShowTimeDTO;
import com.codingduo.cinemabookingticket.model.ShowTime;
import com.codingduo.cinemabookingticket.repository.ShowtimeRepository;
import com.codingduo.cinemabookingticket.service.IShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowTimeService implements IShowTimeService {
    @Autowired
    ShowtimeRepository showtimeRepository;
    @Override
    public List<ShowTimeDTO> getShowTimeByMovieAndDateShow(Long id, Date date) {
        List<ShowTimeDTO> showTimeDTOList = new ArrayList<>();
        List<ShowTime> showTimeList = showtimeRepository.findShowTimesByMovieIdAndDateShow(id, date);
        for(ShowTime showTime : showTimeList) {
            showTimeDTOList.add(convertToShowTimeDTO(showTime));
        }
        return showTimeDTOList;
    }

    @Override
    public ShowTime getShowTimeByIdAndRoomId(Long showtimeId, Long roomId) {
        return showtimeRepository.findShowTimeByIdAndRoomId(showtimeId, roomId);
    }

    private ShowTimeDTO convertToShowTimeDTO(ShowTime showTime) {
        ShowTimeDTO showTimeDTO = new ShowTimeDTO();
        showTimeDTO.setId(showTime.getId());
        showTimeDTO.setDateShow(showTime.getDateShow());
        showTimeDTO.setTimeShow(showTime.getTimeShow());
        showTimeDTO.setDeleted(showTime.isDeleted());
        showTimeDTO.setIdRoom(showTime.getRoom().getId());
        showTimeDTO.setIdTicket(showTime.getTicket().getId());
        showTimeDTO.setIdMovie(showTime.getMovie().getId());
        showTimeDTO.setPrice(showTime.getTicket().getPrice());
        return showTimeDTO;
    }
}
