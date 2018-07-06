package com.example.demo.web.controller;


import com.example.demo.domain.entity.House;
import com.example.demo.domain.entity.Msg;
import com.example.demo.domain.entity.Student;
import com.example.demo.domain.entity.User;
import com.example.demo.service.HouseService;
import com.example.demo.service.StudentService;
import com.example.demo.service.UserService;
import com.example.demo.web.controller.FormEntity.HouseForm;
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
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    HouseService houseService;
    UserService userService;

    //消息页面
    @RequestMapping(value = "/msg/{userid}",method = RequestMethod.GET)
    public String showMsg(@PathVariable String userid,Model model){
        long userid_l = Long.parseLong(userid);
        List<Msg> list = studentService.showAllMsg(userid_l);
        model.addAttribute("msgList",list);
        return "msg";
    }
    //学生端注册
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String showRegisterForm(Model model){
        model.addAttribute("user",new Student());
        return "stu_register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String processRegister(@Valid Student user, Errors errors, RedirectAttributes model) throws IOException {
//        if (errors.hasErrors())
//            return "register";
        if (!studentService.register(user)) {
            errors.rejectValue("userName", "用户已存在");
            return "stu_register";
        }
        else {
            model.addFlashAttribute(user);
            return "redirect:/student/"+user.getUserName()+user.getId();
        }
    }

    //学生端登录
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String showLoginForm(Model model){
        model.addAttribute("student",new Student());
        return "stu_login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String processLogin(@Valid Student userForm,Error errors,RedirectAttributes model){
        Student user;
        if((user=studentService.login(userForm.getUserName(),userForm.getPassword()))==null) {
            return "stu_login";
        }

        model.addFlashAttribute("student",user);
        return "redirect:/student/"+user.getUserName()+"/"+user.getId();
    }

    //学生端主页
    @RequestMapping(value = "/{userName}/{userid}",method =RequestMethod.GET)
    public String userProfile(@PathVariable String userName, Model model){
        if(!model.containsAttribute("student")){
            Student user = studentService.getUserDetail(userName);
            model.addAttribute(user);
        }
//        Student user = studentService.getUserDetail(userName);
//        model.addAttribute("student",user);
        return "stu_profile";
    }

    @RequestMapping(value = "/r/{userName}/{userid}/{houseid}",method = RequestMethod.GET)
    public String showStuRentHouse(@PathVariable String userName,@PathVariable String userid,@PathVariable String houseid,Model model){
        //参数的userid是学生的id
        long houseid_l = Long.parseLong(houseid);
        long userid_l = Long.parseLong(userid);
        House house = studentService.findHouseById(houseid_l);
        Msg msg = new Msg();
        msg.setHouseid(houseid_l);
        msg.setStuid(userid_l);
        msg.setUserid(house.getUserId());
        msg.setAddress(house.getAddress());
        studentService.msgSave(msg);
        model.addAttribute("studentid",userid_l);
        return "stu_r_house";

    }
}
