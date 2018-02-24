package com.ndchack.travelhunt.controller;

import com.ndchack.travelhunt.Util.Configuration;
import com.ndchack.travelhunt.service.UserAncillaryService;
import com.ndchack.travelhunt.ui.domain.Ancilaries.UserAncillaryResponse;
import org.joda.time.DateTime;
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

    @RequestMapping(value="/list/ancillaries",method = RequestMethod.POST)
    public UserAncillaryResponse getAncillaryList(){

        if(new DateTime().isBefore(Configuration.departureTime) || new DateTime().isAfter(Configuration.returnDepartureTime.minusHours(6))){
            return new UserAncillaryResponse();
        } else if (Configuration.userSelectedAncillary.size() > 0 && !Configuration.userSelectedAncillary.isEmpty()) {
            return userAncillaryService.retrieveUserAncillaryDetails();
        } else if (Configuration.airlineAncillary.size() > 0 && !Configuration.airlineAncillary.isEmpty()) {
            return userAncillaryService.retrieveAirlineAncillaryDetails();
        } else {
            return userAncillaryService.retrieveAirAncillaryDetailsWithApiCall();
        }

    }

    @RequestMapping(value="/get/mock/ancillaries",method = RequestMethod.GET)
    public UserAncillaryResponse getmockAirAncillary(){
        return userAncillaryService.retrieveAirAncillaryDetails();
    }




}
