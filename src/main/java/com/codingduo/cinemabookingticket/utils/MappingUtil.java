package com.codingduo.cinemabookingticket.utils;

import com.codingduo.cinemabookingticket.dto.SeatDTO;
import com.codingduo.cinemabookingticket.dto.ShowTimeDTO;
import com.codingduo.cinemabookingticket.model.HistoryDetail;
import com.codingduo.cinemabookingticket.model.Seat;
import com.codingduo.cinemabookingticket.model.ShowTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MappingUtil {
    public static List<SeatDTO> classifySeat(List<Seat> seatList, List<HistoryDetail> historyDetailList) {


        List<SeatDTO> seatDTOList = new ArrayList<>();

        Map<Long, SeatDTO> seatMap = new HashMap<>();
        int i = 0;
        for (Seat seat : seatList) {
            SeatDTO seatDTO = convertToDTO(seat, false);
            seatMap.put(seat.getId(), seatDTO);
            seatDTOList.add(seatDTO);
        }
        for(HistoryDetail historyDetail : historyDetailList) {
            Long seatId = historyDetail.getSeat().getId();
            SeatDTO seat = seatMap.get(seatId);
            if(seat != null) {
                seat.setSold(true);
            }
        }
        return seatDTOList;
    }
    public static SeatDTO convertToDTO(Seat seat, boolean isSold) {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setId(seat.getId());
        seatDTO.setSold(isSold);
        return seatDTO;
    }

    public static ShowTimeDTO convertToDTO(ShowTime showTime) {
        ShowTimeDTO showTimeDTO = new ShowTimeDTO();
        showTimeDTO.setId(showTime.getId());
        showTimeDTO.setTimeShow(showTime.getTimeShow());
        showTimeDTO.setDateShow(showTime.getDateShow());
        showTimeDTO.setPrice(showTime.getTicket().getPrice());
        showTimeDTO.setDeleted(showTime.isDeleted());
        showTimeDTO.setIdMovie(showTime.getMovie().getId());
        showTimeDTO.setIdRoom(showTime.getRoom().getId());
        showTimeDTO.setIdTicket(showTime.getTicket().getId());
        return showTimeDTO;
    }
}
