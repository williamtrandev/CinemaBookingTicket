package com.codingduo.cinemabookingticket.api;

import com.codingduo.cinemabookingticket.dto.TheaterWithShowtimeDTO;
import com.codingduo.cinemabookingticket.model.Theater;
import com.codingduo.cinemabookingticket.service.ITheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/theater")
public class TheaterApi {
    @Autowired
    private ITheaterService theaterService;

    @GetMapping("/getAllByDateAndMovieId")
    public List<TheaterWithShowtimeDTO> getAllByDateAndMovieId(@RequestParam(name = "date") Date date,
                                                               @RequestParam(name = "id") Long id) {
        return theaterService.getAllByDateAndMovieId(date, id);
    }

}
