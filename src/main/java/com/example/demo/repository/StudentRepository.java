package com.example.demo.repository;

import com.example.demo.domain.entity.House;
import com.example.demo.domain.entity.Msg;
import com.example.demo.domain.entity.Student;

import java.util.List;


public interface StudentRepository {
    public void save(Student user);
    public Student findByUserName(String userName);
    public Student findByUserId(long userId);
    public void msgSave(Msg msg);
    public House findHouseById(long id);
    public List<Msg> showAllMsg(long stuid);
}
