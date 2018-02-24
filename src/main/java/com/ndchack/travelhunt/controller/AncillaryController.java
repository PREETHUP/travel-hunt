package com.ndchack.travelhunt.controller;

import com.ndchack.travelhunt.Util.Configuration;
import com.ndchack.travelhunt.service.UserAncillaryService;
import com.ndchack.travelhunt.ui.domain.Ancilaries.AncillaryIdWrapper;
import com.ndchack.travelhunt.ui.domain.Ancilaries.UserAncillaryResponse;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by a-6281 on 2/24/18.
 */
@RestController
public class AncillaryController {

    @Autowired
    private UserAncillaryService userAncillaryService;

    @RequestMapping(value="/update/ancillaries",method = RequestMethod.POST)
    public UserAncillaryResponse updateUserAncillaryAmount(@RequestParam(value = "tasksFinished") Integer tasksFinished, @RequestParam(value = "totalTasks") Integer totalTasks){
        userAncillaryService.updateUserAncillaryAmount(tasksFinished, totalTasks);
        return  userAncillaryService.retrieveUserAncillaryDetails();

    }

    @RequestMapping(value="/list/ancillaries",method = RequestMethod.POST)
    public UserAncillaryResponse getAncillaryList(){
        return userAncillaryService.getAncillaryResponse();
    }

    @RequestMapping(value="/set/user/ancillaries",method = RequestMethod.POST)
    public UserAncillaryResponse setUserAncillary(@RequestBody AncillaryIdWrapper ancillaryIdWrapper){
        userAncillaryService.setUserAncillary(ancillaryIdWrapper.getAncillaryIds());
        return userAncillaryService.retrieveUserAncillaryDetails();
    }

    @RequestMapping(value="/get/mock/ancillaries",method = RequestMethod.GET)
    public UserAncillaryResponse getmockAirAncillary(){
        return userAncillaryService.retrieveAirlineAncillaryDetails();
    }

}
