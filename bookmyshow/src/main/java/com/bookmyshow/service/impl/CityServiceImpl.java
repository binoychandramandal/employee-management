package com.bookmyshow.service.impl;

import com.bookmyshow.entity.City;
import com.bookmyshow.repository.CityRepository;
import com.bookmyshow.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl extends BaseServiceImpl<City,Long> implements CityService {
    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
        setJpaRepository(cityRepository);
    }
}
