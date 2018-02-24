package com.ndchack.travelhunt.controller;

import com.ndchack.travelhunt.service.UserAncillaryService;
import com.ndchack.travelhunt.ui.domain.Ancilaries.UserAncillaryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by a-6281 on 2/24/18.
 */
@RestController
public class AncillaryController {

    @Autowired
    private UserAncillaryService userAncillaryService;

    @RequestMapping(value="/retrive/ancillaries",method = RequestMethod.POST)
    public String getAncillary(@RequestParam(value = "id") String Id){
        //Service call
        return "index";
    }

    @RequestMapping(value="/update/ancillaries",method = RequestMethod.POST)
    public UserAncillaryResponse updateAncillaryAmount(@RequestParam(value = "tasksFinished") Integer tasksFinished, @RequestParam(value = "totalTasks") Integer totalTasks){
        userAncillaryService.updateUserAncillary(tasksFinished, totalTasks);
        return  userAncillaryService.retrieveUserAncillaryDetails();

    }




}
