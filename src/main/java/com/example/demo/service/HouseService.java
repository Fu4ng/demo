package com.example.demo.service;

import com.example.demo.domain.entity.House;
import com.example.demo.web.controller.FormEntity.HouseForm;

import java.util.List;

public interface HouseService{

    public List<House> getHouseByUserName(String userName);
    public void resetHouse(long id,HouseForm houseForm);
    public void saveHouse(HouseForm houseForm);
    public void deleteHouse(long id);
    public House getHouseById(long id);
    public List<House> showAll();
}
