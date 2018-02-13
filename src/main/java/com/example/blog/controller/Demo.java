package com.example.blog.controller;

import com.example.blog.domain.Navigation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: changle
 * Date: 2018/1/2
 * Time: 19:58
 */
@Controller
public class Demo {

    @RequestMapping("/")
    public ModelAndView index(){
        List<Navigation> navList =new ArrayList<>();
        navList.add(new Navigation("111","111"));
        navList.add(new Navigation("222","222"));
        navList.add(new Navigation("333","333"));
        navList.add(new Navigation("111","111"));
        navList.add(new Navigation("222","222"));
        navList.add(new Navigation("333","333"));
        navList.add(new Navigation("111","111"));
        navList.add(new Navigation("222","222"));
        navList.add(new Navigation("333","333"));
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("navList", navList);
        modelAndView.addObject("home","home");
        modelAndView.addObject("about","about");
        modelAndView.addObject("login","login");
        return modelAndView;
    }
}
