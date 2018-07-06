package com.example.demo.repository;
import com.example.demo.domain.entity.House;
import com.example.demo.domain.entity.Msg;
import com.example.demo.domain.entity.User;

import java.util.List;

public interface UserRepository {
    public void save(User user);
    public User findByUserName(String userName);
    public User findByUserId(long userId);
    public void msgSave(Msg msg);
    public House findHouseById(long id);
    public List<Msg> showAllMsg(long userid);
}
