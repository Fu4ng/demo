package com.example.demo.web.controller;

import com.example.demo.domain.entity.House;
import com.example.demo.domain.entity.User;
import com.example.demo.service.HouseService;
import com.example.demo.service.UserService;
import com.example.demo.web.controller.FormEntity.HouseForm;
import com.sun.xml.internal.ws.developer.SchemaValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOError;
import java.util.List;

@Controller
@RequestMapping("/house")
public class HouseController {

    @Autowired
    HouseService houseService;
    UserService userService;
    //============
    //学生端
    //==============
    @RequestMapping(value="/all/{studentName}/{studentid}",method = RequestMethod.GET)
    public String getHouseAll(@PathVariable String studentName,@PathVariable String studentid,Model model){
        List<House> houseList = houseService.showAll();
        model.addAttribute("houseList",houseList);
        model.addAttribute("studentName",studentName);
        model.addAttribute("studentid",studentid);
        return "house_all";
    }

    //============
    //房主端
    //==============
    @RequestMapping(value = "/list/{userName}/{userid}",method = RequestMethod.GET)
    public String getHouseList(@PathVariable String userName,@PathVariable String userid ,Model model){
        List<House> houseList=houseService.getHouseByUserName(userName);
        long userid_l = Long.parseLong(userid);
        model.addAttribute("userid",userid_l);
        model.addAttribute("houseList",houseList);
        model.addAttribute("userName",userName);
        return "house_list";
    }

    //出租房屋
    @RequestMapping(value = "/r/{userName}/{userid}",method = RequestMethod.GET)
    public String showHouseResForm(@PathVariable String userName,@PathVariable String userid ,Model model){
        long userid_l = Long.parseLong(userid);
        HouseForm houseForm = new HouseForm();
        houseForm.setUserName(userName);
        //获取用户的ID
        houseForm.setUserId(userid_l);
        model.addAttribute("houseForm",houseForm);
        return "house_r";
    }
    @RequestMapping(value = "/r/{userName}/{userid}",method = RequestMethod.POST)
    public String processHouseRes(@Valid HouseForm houseForm, Errors errors, RedirectAttributes model) throws IOError{
//        User user ;
        //如果密码错误
//        if ((user=userService.loginById(houseForm.getUserId(),houseForm.getPassword()))==null)
//            return "house_r";
        if(errors.hasErrors())
            return "house_r";

        else {
//            model.addFlashAttribute(user);
            houseService.saveHouse(houseForm);
//            model.addFlashAttribute("userName",houseForm.getUserName());
            return "redirect:/house/s/"+houseForm.getUserName()+'/'+houseForm.getUserId();
        }
    }
    //房屋发布成功页面
    @RequestMapping(value = "/s/{userName}/{userid}",method = RequestMethod.GET)
    public String processHouseRes(@PathVariable String userName,@PathVariable String userid,Model model){
        model.addAttribute("userName",userName);
        model.addAttribute("userid",userid);
        return "house_s";
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable long id,Model model){
        House house;
        house = houseService.getHouseById(id);
        houseService.deleteHouse(id);
        model.addAttribute("house",house);
        model.addAttribute("id",id);
        return "delete";
    }

    //修改房屋
    @RequestMapping(value = "/{userName}/{houseid}",method = RequestMethod.GET)
    public String showResetHouse(@PathVariable String houseid,@PathVariable String userName, Model model){
//        House_Re houseRe = new House_Re();
        long houseid_l = Long.parseLong(houseid);
//        houseRe.setHouseid(houseid_l);
//        houseRe.setUserName(userName);
//        model.addAttribute("houseRe",houseRe);
//        model.addAttribute("userName",userName);
//        model.addAttribute("houseid",houseid_l);
//        return "house_reset";
        HouseForm houseForm = new HouseForm();
        House t_house = houseService.getHouseById(houseid_l);
        //将t_house的各属性值赋给houseForm
        houseForm.setPnum(t_house.getPnum());
        houseForm.setDetail(t_house.getDetail());
        houseForm.setAddress(t_house.getAddress());
        houseForm.setType(t_house.getType());
        houseForm.setHouseid(houseid_l);
        houseForm.setUserName(userName);
        //
        model.addAttribute("houseForm",houseForm);
        model.addAttribute("userName",userName);
        model.addAttribute("houseid",houseid_l);
        return "house_reset";


    }

    @RequestMapping(value = "/{userName}/{houseid}",method = RequestMethod.POST)
    public String processResetHouse(@Valid HouseForm houseForm, Errors errors,RedirectAttributes model) {
//        if(errors.hasErrors())
//            return "redirect:/user/login";
//        else {
//            HouseForm houseForm = new HouseForm();
//            houseForm.setAddress(houseRe.getAddress());
//            houseForm.setDetail(houseRe.getDetail());
//            houseForm.setPnum(houseRe.getPnum());
//            houseForm.setType(houseRe.getType());
//            houseService.resetHouse(houseRe.getHouseid(),houseForm);
////            List<House> houseList=houseService.getHouseByUserName(houseRe.getUserName());
////            model.addAttribute("houseList",houseList);
////            model.addAttribute("userName",houseRe.getUserName());
//
//            return "redirect:/";
//        }
//        if(errors.hasErrors())
//            return "house_r";
//
//        else {
//            long houseid_l = Long.parseLong(houseid);
//            model.addFlashAttribute(user);
            houseService.resetHouse(houseForm.getHouseid(),houseForm);
            return "redirect:/house/"+houseForm.getUserName();
//        }
    }


}
