package com.codingduo.cinemabookingticket.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowTimeResponse {

    private LocalTime startShowing;
    private LocalTime endShowing;


}