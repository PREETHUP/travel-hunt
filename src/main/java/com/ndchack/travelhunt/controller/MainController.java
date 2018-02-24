package com.ndchack.travelhunt.controller;

import com.ndchack.travelhunt.Util.Configuration;
import com.ndchack.travelhunt.job.ScheduledJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private Configuration configuration;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homepage() {
        return "Landing";
    }

    @RequestMapping(value = "/update/discount", method = RequestMethod.GET)
    public String updateDiscount(@RequestParam(value = "value") String discount) {
        configuration.discount = Integer.parseInt(discount);
        log.info(String.valueOf(configuration.discount));
        return "index";
    }

}
