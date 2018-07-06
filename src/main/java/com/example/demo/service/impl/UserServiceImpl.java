package com.example.demo.service.impl;


import com.example.demo.domain.entity.House;
import com.example.demo.domain.entity.Msg;
import com.example.demo.domain.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;


    @Override
    public List<Msg> showAllMsg(long stuid){
        return userRepository.showAllMsg(stuid);
    }
    @Override
    public House findHouseById(long id){
        return userRepository.findHouseById(id);
    }
    @Override
    public User login(String userName,String password){
        User user;
        user=userRepository.findByUserName(userName);
        if(user!=null && user.getPassword().equals(password)){
            System.out.println(user.getUserName());
            return user;
        }
        return null;
    }

    @Override
    public User loginById(long userId,String password){
        User user;
//        user = userRepository.findByUserId(userId);
        if ((user = userRepository.findByUserId(userId))!=null
                && user.getPassword().equals(password)){
            System.out.println(user.getUserName());
            return user;
        }
        return null;
    }

    @Override
    public boolean register(User user){
        if((userRepository.findByUserName(user.getUserName()))==null){
            //检查是否用户名重复
            userRepository.save(user);
            return true;
        }
        return false;

    }

    @Override
    public User getUserDetail(String userName){
        return userRepository.findByUserName(userName);
    }
    @Override
    public void msgSave(Msg msg){
        userRepository.msgSave(msg);
    }
}
