package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.dto.ComboDTO;
import com.codingduo.cinemabookingticket.model.Combo;
import com.codingduo.cinemabookingticket.repository.ComboRepository;
import com.codingduo.cinemabookingticket.service.IComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComboService implements IComboService {
    @Autowired
    private ComboRepository comboRepository;
    @Override
    public Combo save(ComboDTO comboDTO) {
        return null;
    }

    @Override
    public List<Combo> getAllByDeleted(boolean deleted) {
        return comboRepository.findAllByDeleted(deleted);
    }
}
