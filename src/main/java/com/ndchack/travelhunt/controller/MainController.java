package com.ndchack.travelhunt.controller;

import com.ndchack.travelhunt.Util.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private Configuration configuration;

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String homepage(){
        return "index";
    }

    @RequestMapping(value="/update/discount",method = RequestMethod.GET)
    public String updateDiscount(@RequestParam(value = "value") String discount){
        configuration.discount = Integer.parseInt(discount);
        System.out.println(configuration.discount);
        return "index";
    }
}
