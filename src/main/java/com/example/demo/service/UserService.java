package com.example.demo.service;

import com.example.demo.domain.entity.House;
import com.example.demo.domain.entity.Msg;
import com.example.demo.domain.entity.User;

import java.util.List;

public interface UserService {
    public User login(String userName, String password);
    public User loginById(long userId,String password);
    public boolean register(User user);
    public User getUserDetail(String username);
    public void msgSave(Msg msg);
    public House findHouseById(long id);
    public List<Msg> showAllMsg(long userid);
}
