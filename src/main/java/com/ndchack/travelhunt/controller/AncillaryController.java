package com.ndchack.travelhunt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by a-6281 on 2/24/18.
 */
@RestController
public class AncillaryController {

    @RequestMapping(value="/retrive/ancillaries",method = RequestMethod.POST)
    public String getAncillary(@RequestParam(value = "id") String id){
        //Service call

        return "index";
    }
}
