package com.codingduo.cinemabookingticket.service;


import com.codingduo.cinemabookingticket.dto.ComboDTO;
import com.codingduo.cinemabookingticket.model.Combo;

public interface IComboService {
    Combo save(ComboDTO comboDTO);

}