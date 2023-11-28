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
        Combo combo = new Combo();
        combo.setNameCombo(comboDTO.getName());
        combo.setDetail(comboDTO.getDetail());
        combo.setImagePath(comboDTO.getImagePath());
        combo.setPrice(comboDTO.getPrice());
        combo.setDeleted(false);
        return comboRepository.save(combo);
    }

    @Override
    public List<Combo> getAllByDeleted(boolean deleted) {
        return comboRepository.findAllByDeleted(deleted);
    }

    @Override
    public Combo delete(Long id) {
        Combo combo = comboRepository.getReferenceById(id);
        combo.setDeleted(true);
        return comboRepository.save(combo);
    }

    @Override
    public Combo update(Long id, ComboDTO comboDTO) {
        Combo combo = comboRepository.getReferenceById(id);
        combo.setNameCombo(comboDTO.getName());
        combo.setDetail(comboDTO.getDetail());
        combo.setPrice(comboDTO.getPrice());
        if (comboDTO.getImagePath() != null) { // Nếu hình không thay đổi
            combo.setImagePath(comboDTO.getImagePath());
        }
        return comboRepository.save(combo);
    }
}
