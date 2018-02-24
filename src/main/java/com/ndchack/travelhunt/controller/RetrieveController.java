package com.ndchack.travelhunt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by a-6281 on 2/24/18.
 */
@Controller
public class RetrieveController {

    @ResponseBody
    @RequestMapping(value="/retrive/trips",method = RequestMethod.POST)
    public String retrive(@RequestParam(value = "orderId") String orderId){
        //Service call

        return "index";
    }

    @RequestMapping(value="/retrive/test/trips",method = RequestMethod.GET)
    public String mockretrive(@RequestParam(value = "orderId") String orderId){
        //Service call

        return "index";
    }
}
