package com.example.demo.service;

import com.example.demo.domain.entity.House;
import com.example.demo.domain.entity.Msg;
import com.example.demo.domain.entity.Student;

import java.util.List;

public interface StudentService {
    public Student login(String userName, String password);
    public Student loginById(long userId,String password);
    public boolean register(Student user);
    public Student getUserDetail(String username);
    public void msgSave(Msg msg);
    public House findHouseById(long id);
    public List<Msg> showAllMsg(long stuid);
}
