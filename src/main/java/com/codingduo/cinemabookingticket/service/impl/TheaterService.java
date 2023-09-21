package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.dto.ShowTimeDTO;
import com.codingduo.cinemabookingticket.dto.TheaterWithShowtimeDTO;
import com.codingduo.cinemabookingticket.model.Room;
import com.codingduo.cinemabookingticket.model.ShowTime;
import com.codingduo.cinemabookingticket.model.Theater;
import com.codingduo.cinemabookingticket.repository.TheaterRepository;
import com.codingduo.cinemabookingticket.service.ITheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
//import java.util.Date;

@Service
public class TheaterService implements ITheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    @Override
    public Theater save(Theater theater) {
        return null;
    }

    @Override
    public List<Theater> getAll() {
        return theaterRepository.findAll();
    }

    @Override
    public List<TheaterWithShowtimeDTO> getAllByDateAndMovieId(Date date, Long id) {
        List<Theater> theaterList = theaterRepository.findTheatersByDateAndMovieId(date, id);
        List<TheaterWithShowtimeDTO> theaterWithShowtimeList = new ArrayList<>();
        for(Theater theater : theaterList) {
            theaterWithShowtimeList.add(convertToDTO(theater, date));
        }
        return theaterWithShowtimeList;
    }

    private TheaterWithShowtimeDTO convertToDTO(Theater theater, Date date) {
        TheaterWithShowtimeDTO theaterWithShowtimeDTO = new TheaterWithShowtimeDTO();
        theaterWithShowtimeDTO.setId(theater.getId());
        theaterWithShowtimeDTO.setName(theater.getName());
        theaterWithShowtimeDTO.setAddress(theater.getAddress());
        List<ShowTimeDTO> showTimeDTOList = new ArrayList<>();
        for(Room room : theater.getRoomList()) {
            for (ShowTime showTime : room.getShowTimeList()) {
                if (showTime.getDateShow().equals(date)) {
                    ShowTimeDTO showTimeDTO = new ShowTimeDTO();
                    showTimeDTO.setId(showTime.getId());
                    showTimeDTO.setDateShow(showTime.getDateShow());
                    showTimeDTO.setTimeShow(showTime.getTimeShow());
                    showTimeDTO.setDeleted(showTime.isDeleted());
                    showTimeDTO.setIdMovie(showTime.getMovie().getId());
                    showTimeDTO.setIdRoom(showTime.getRoom().getId());
                    showTimeDTO.setIdTicket(showTime.getTicket().getId());
                    showTimeDTOList.add(showTimeDTO);
                }
            }
        }
        theaterWithShowtimeDTO.setShowTimeList(showTimeDTOList);
        return theaterWithShowtimeDTO;
    }

    private List<ShowTimeDTO> convertToListDTO(List<ShowTime> list) {
        List<ShowTimeDTO> showTimeDTOList = new ArrayList<>();
        for(ShowTime showTime : list) {
            ShowTimeDTO showTimeDTO = new ShowTimeDTO();
            showTimeDTO.setId(showTime.getId());
            showTimeDTO.setDateShow(showTime.getDateShow());
            showTimeDTO.setTimeShow(showTime.getTimeShow());
            showTimeDTO.setDeleted(showTime.isDeleted());
            showTimeDTO.setIdMovie(showTime.getMovie().getId());
            showTimeDTO.setIdRoom(showTime.getRoom().getId());
            showTimeDTO.setIdTicket(showTime.getTicket().getId());
            showTimeDTOList.add(showTimeDTO);
        }
        return showTimeDTOList;
    }

//    public List<TheaterWithShowtimeDTO> test(Date date, Long id) {
//        return theaterRepository.findAllTheatersByDateAndMovieId(date, id);
//    }
}
