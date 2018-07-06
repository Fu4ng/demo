package com.example.demo.web.controller;


import com.example.demo.domain.entity.Msg;
import com.example.demo.domain.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.web.controller.FormEntity.UserForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    //消息页面
    @RequestMapping(value = "/msg/{userid}",method = RequestMethod.GET)
    public String showMsg(@PathVariable String userid,Model model){
        long userid_l = Long.parseLong(userid);
        List<Msg> list = userService.showAllMsg(userid_l);
        model.addAttribute("msgList",list);
        return "msg";
    }
    //注册
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String showRegisterForm(Model model){
        model.addAttribute(new User());
        return "register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String processRegister(@Valid User user, Errors errors, RedirectAttributes model) throws IOException {
        if (errors.hasErrors())
            return "register";
        else if (!userService.register(user)) {
            errors.rejectValue("userName", "用户已存在");
            return "register";
        }
        else {
            model.addFlashAttribute(user);
            return "redirect:/user/"+user.getUserName();
        }
    }

    @RequestMapping(value = "/{userName}",method =RequestMethod.GET)
    public String userProfile(@PathVariable String userName,Model model){
        if(!model.containsAttribute("user")){
            User user = userService.getUserDetail(userName);
            model.addAttribute(user);
        }
        return "user_profile";
    }


    //登陆

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String showLoginForm(Model model){
        model.addAttribute(new User());
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String processLogin(@Valid User userForm,Error errors,RedirectAttributes model){
        User user;
        if((user=userService.login(userForm.getUserName(),userForm.getPassword()))==null) {
            return "login";
        }

        model.addFlashAttribute(user);
        return "redirect:/user/"+user.getUserName();
    }
}
