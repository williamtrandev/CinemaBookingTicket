package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.model.Banner;
import com.codingduo.cinemabookingticket.repository.BannerRepository;
import com.codingduo.cinemabookingticket.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerService implements IBannerService {

    @Autowired
    BannerRepository bannerRepository;

    @Override
    public List<Banner> getAll() {
        return bannerRepository.findAll();
    }
}
