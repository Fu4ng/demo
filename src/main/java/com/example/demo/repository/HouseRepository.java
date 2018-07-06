package com.example.demo.repository;

import com.example.demo.domain.entity.House;
import com.example.demo.web.controller.FormEntity.HouseForm;

import java.util.List;

public interface HouseRepository {
    public List<House> findHouseByUserId(Long userId);
    public void save(HouseForm houseForm);
    public void delete(long id);
    public void reset(long id,HouseForm houseForm);
    public House findHouseById(long id);
    public List<House> showAll();
}
