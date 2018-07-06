package com.example.demo.service.impl;

import com.example.demo.domain.entity.House;
import com.example.demo.domain.entity.Msg;
import com.example.demo.domain.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Msg> showAllMsg(long stuid){
        return studentRepository.showAllMsg(stuid);
    }
    @Override
    public House findHouseById(long id){
        return studentRepository.findHouseById(id);
    }
    @Override
    public Student login(String userName, String password){
        Student  user;
        user=studentRepository.findByUserName(userName);
        if(user!=null && user.getPassword().equals(password)){
            System.out.println(user.getUserName());
            return user;
        }
        return null;
    }

    @Override
    public Student  loginById(long userId,String password){
        Student  user;
//        user = userRepository.findByUserId(userId);
        if ((user = studentRepository.findByUserId(userId))!=null
                && user.getPassword().equals(password)){
            System.out.println(user.getUserName());
            return user;
        }
        return null;
    }

    @Override
    public boolean register(Student user){
        if((studentRepository.findByUserName(user.getUserName()))==null){
            //检查是否用户名重复
            studentRepository.save(user);
            return true;
        }
        return false;

    }

    @Override
    public Student getUserDetail(String userName){
        return studentRepository.findByUserName(userName);
    }

    @Override
    public void msgSave(Msg msg){
        studentRepository.msgSave(msg);
    }
}
