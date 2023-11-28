package com.codingduo.cinemabookingticket.api;


import com.codingduo.cinemabookingticket.dto.ComboDTO;
import com.codingduo.cinemabookingticket.dto.MovieDTO;
import com.codingduo.cinemabookingticket.model.Combo;
import com.codingduo.cinemabookingticket.service.IComboService;
import com.codingduo.cinemabookingticket.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/combo")
public class ComboApi {
    @Autowired
    private IComboService comboService;

    @PostMapping
    public ResponseEntity<Combo> save(
            @ModelAttribute ComboDTO comboDTO,
            @RequestParam("image") MultipartFile image
    ) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        comboDTO.setImagePath(fileName);
        String uploadDir = "public/combo";
        FileUploadUtil.saveFile(uploadDir, fileName, image);

        return new ResponseEntity<>(comboService.save(comboDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Combo> update(
            @PathVariable("id") Long id,
            @ModelAttribute ComboDTO comboDTO,
            @RequestParam("image") MultipartFile image
    ) throws IOException {
        if (!image.isEmpty()) {
            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
            comboDTO.setImagePath(fileName);
            String uploadDir = "public/combo";
            FileUploadUtil.saveFile(uploadDir, fileName, image);
        }
        return new ResponseEntity<>(comboService.update(id, comboDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Combo> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(comboService.delete(id), HttpStatus.OK);
    }
}
