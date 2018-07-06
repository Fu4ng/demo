package com.example.demo.service.impl;

import com.example.demo.domain.entity.House;
import com.example.demo.repository.HouseRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.HouseService;
import com.example.demo.web.controller.FormEntity.HouseForm;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    HouseRepository houseRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<House> getHouseByUserName(String userName){
        return houseRepository.findHouseByUserId(userRepository.findByUserName(userName).getId());
    }
    public void resetHouse(long id,HouseForm houseForm){
        houseRepository.reset(id,houseForm);
    }
    @Override
    public void saveHouse(HouseForm houseForm){
        houseRepository.save(houseForm);
    }

    @Override
    public void deleteHouse(long id){
        houseRepository.delete(id);
    }

    @Override
    public House getHouseById(long id){
        House house;
        house = houseRepository.findHouseById(id);
        return house;
    }

    @Override
    public List<House> showAll(){
        return houseRepository.showAll();
    }
}
