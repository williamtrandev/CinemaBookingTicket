package com.codingduo.cinemabookingticket.service;


import com.codingduo.cinemabookingticket.dto.ComboDTO;
import com.codingduo.cinemabookingticket.model.Combo;

import java.util.List;

public interface IComboService {
    Combo save(ComboDTO comboDTO);
    List<Combo> getAllByDeleted(boolean deleted);

}
