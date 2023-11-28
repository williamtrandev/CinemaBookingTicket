package com.codingduo.cinemabookingticket.api;

import com.codingduo.cinemabookingticket.api.request.ShowtimeRequest;
import com.codingduo.cinemabookingticket.api.response.ShowTimeResponse;
import com.codingduo.cinemabookingticket.dto.ShowTimeDTO;
import com.codingduo.cinemabookingticket.model.Movie;
import com.codingduo.cinemabookingticket.model.Room;
import com.codingduo.cinemabookingticket.model.ShowTime;
import com.codingduo.cinemabookingticket.model.Ticket;
import com.codingduo.cinemabookingticket.service.IMovieService;
import com.codingduo.cinemabookingticket.service.IRoomService;
import com.codingduo.cinemabookingticket.service.IShowTimeService;
import com.codingduo.cinemabookingticket.service.ITicketService;
import com.codingduo.cinemabookingticket.utils.MappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/v1/showtime")
public class ShowTimeAPI {
    @Autowired
    private IShowTimeService showTimeService;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private ITicketService ticketService;

    @Autowired
    private IMovieService movieService;

    @PostMapping({"", "/"})
    public ResponseEntity<?> addShowTime(@RequestBody ShowtimeRequest request) {
        List<ShowTime> showTimeList = showTimeService.getAllByDateShowAndRoom(request.getDateShow(), request.getRoomId());
        for(ShowTime showTime : showTimeList) {
            int durationShowTime = showTime.getMovie().getDuration();
            Time timeShowing = showTime.getTimeShow();
            LocalTime startShowing = timeShowing.toLocalTime();
            // Thời gian phim trong phòng chiếu kết thúc
            LocalTime endShowing = startShowing.plusMinutes(durationShowTime);

            LocalTime startShowingAdd = request.getTimeShow().toLocalTime();
            // Thời gian kết thúc suất chiếu muốn thêm
            LocalTime endShowingAdd = startShowingAdd.plusMinutes(request.getDuration());

            // Nếu thời gian kết thúc suất chiếu của phim muốn thêm nằm
            // trong khoảng phim bắt đầu và kết thúc thì không cho thêm
            // Hoặc thời gian bắt đầu nằm trong khoảng thời gian đang chiếu
            if((endShowingAdd.isAfter(startShowing) && endShowingAdd.isBefore(endShowing)) ||
                    (startShowingAdd.isAfter(startShowing) && startShowingAdd.isBefore(endShowing))) {
                ShowTimeResponse showTimeResponse = new ShowTimeResponse(startShowing, endShowing);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(showTimeResponse);
            }
        }
        ShowTime showTime = new ShowTime();
        showTime.setTimeShow(request.getTimeShow());
        showTime.setDateShow(request.getDateShow());
        showTime.setDeleted(false);
        Room room = roomService.getOne(request.getRoomId());
        Ticket ticket = ticketService.getOne(request.getTicketId());
        Movie movie = movieService.getById(request.getMovieId());
        showTime.setMovie(movie);
        showTime.setRoom(room);
        showTime.setTicket(ticket);
        ShowTime savedShowTime = showTimeService.save(showTime);
        return ResponseEntity.ok(MappingUtil.convertToDTO(savedShowTime));
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> editShowTime(@RequestBody ShowtimeRequest request, @PathVariable(name = "id") Long id) {
        List<ShowTime> showTimeList = showTimeService.getAllByDateShowAndRoom(request.getDateShow(), request.getRoomId());
        for(ShowTime showTime : showTimeList) {
            int durationShowTime = showTime.getMovie().getDuration();
            Time timeShowing = showTime.getTimeShow();
            LocalTime startShowing = timeShowing.toLocalTime();
            // Thời gian phim trong phòng chiếu kết thúc
            LocalTime endShowing = startShowing.plusMinutes(durationShowTime);

            LocalTime startShowingAdd = request.getTimeShow().toLocalTime();
            // Thời gian kết thúc suất chiếu muốn thêm
            LocalTime endShowingAdd = startShowingAdd.plusMinutes(request.getDuration());

            // Nếu thời gian kết thúc suất chiếu của phim muốn thêm nằm
            // trong khoảng phim bắt đầu và kết thúc thì không cho thêm
            // Hoặc thời gian bắt đầu nằm trong khoảng thời gian đang chiếu
            if((endShowingAdd.isAfter(startShowing) && endShowingAdd.isBefore(endShowing)) ||
                    (startShowingAdd.isAfter(startShowing) && startShowingAdd.isBefore(endShowing))) {
                ShowTimeResponse showTimeResponse = new ShowTimeResponse(startShowing, endShowing);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(showTimeResponse);
            }
        }
        ShowTime showTime = showTimeService.getOne(id);
        showTime.setTimeShow(request.getTimeShow());
        showTime.setDateShow(request.getDateShow());
        showTime.setDeleted(false);
        Room room = roomService.getOne(request.getRoomId());
        Ticket ticket = ticketService.getOne(request.getTicketId());
        Movie movie = movieService.getById(request.getMovieId());
        showTime.setMovie(movie);
        showTime.setRoom(room);
        showTime.setTicket(ticket);
        ShowTime savedShowTime = showTimeService.save(showTime);
        return ResponseEntity.ok(MappingUtil.convertToDTO(savedShowTime));
    }
}
