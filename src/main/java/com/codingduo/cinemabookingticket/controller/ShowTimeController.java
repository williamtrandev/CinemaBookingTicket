package com.codingduo.cinemabookingticket.controller;

import com.codingduo.cinemabookingticket.dto.ComboDTO;
import com.codingduo.cinemabookingticket.dto.MovieDTO;
import com.codingduo.cinemabookingticket.dto.SeatDTO;
import com.codingduo.cinemabookingticket.model.Combo;
import com.codingduo.cinemabookingticket.model.HistoryDetail;
import com.codingduo.cinemabookingticket.model.Seat;
import com.codingduo.cinemabookingticket.model.ShowTime;
import com.codingduo.cinemabookingticket.service.*;
import com.codingduo.cinemabookingticket.utils.MappingUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/booking")
public class ShowTimeController {
    @Autowired
    private IShowTimeService showTimeService;

    @Autowired
    private ISeatService seatService;

    @Autowired
    private IHistoryDetailService historyDetailService;

    @Autowired
    private IComboService comboService;

    @Autowired
    private IMailService mailService;

    @GetMapping("")
    public String bookingPage(Principal principal,
                              HttpSession session, Model model,
                              @RequestParam(name="id") Long idRoom,
                              @RequestParam(name="showtime") Long idShowtime) {
        // Nếu chưa đăng nhập thì bắt đăng nhập rồi mới cho đặt vé
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
//            return "redirect:/login";
//        }
        ShowTime showTimeCheck = showTimeService.getShowTimeByIdAndRoomId(idShowtime, idRoom);
        if(showTimeCheck == null) {
            return "404";
        }

        // Title
        model.addAttribute("title", "Chọn ghế");

        // Lấy dữ liệu từ session
        MovieDTO movie = (MovieDTO) session.getAttribute("movie");

        if(movie == null) return "redirect:/";
        model.addAttribute("infoMovie", movie);

        // Tag
        String tagName = (String) session.getAttribute("tagName");
        model.addAttribute("tagName", tagName);

        // Thể loại
        String genre = (String) session.getAttribute("genre");
        model.addAttribute("genre", genre);
//        roomService.getOne()

        // Ghế
        List<Seat> seatList = seatService.getAllByRoomId(idRoom);

        // Lịch sử ghế đặt
        List<HistoryDetail> historyDetailList = historyDetailService.getAllByShowTimeId(idShowtime);

        // Phân loại ghế
        List<SeatDTO> classifySeatList = MappingUtil.classifySeat(seatList, historyDetailList);

        // Đẩy qua view
        model.addAttribute("seats", classifySeatList);

        // Link css
        model.addAttribute("links", new String[]{"booking.css"});

        // Link script
        model.addAttribute("scripts", new String[]{"booking.js"});
        return "booking";
    }

    @GetMapping("/chooseFood")
    public String chooseFood(Model model) {
        model.addAttribute("title", "Chọn bắp nước");
        model.addAttribute("links", new String[]{"chooseFood.css"});
        model.addAttribute("scripts", new String[]{"booking.js", "chonbapnuoc.js"});
        // Lấy danh sách các combo chưa bị xóa
        List<Combo> comboList = comboService.getAllByDeleted(false);
        model.addAttribute("combo", comboList);
        return "choose_food";
    }


}
