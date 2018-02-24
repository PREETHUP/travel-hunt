package com.ndchack.travelhunt.controller;

import com.ndchack.travelhunt.ui.domain.retrive.RetrieveRateDetails;
import com.ndchack.travelhunt.ui.domain.retrive.RetrieveUserResponse;
import com.ndchack.travelhunt.ui.domain.retrive.TravelerDetail;
import com.ndchack.travelhunt.ui.domain.retrive.TripDetail;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by a-6281 on 2/24/18.
 */
@RestController
public class RetrieveController {

    @RequestMapping(value="/retrieve/trips",method = RequestMethod.POST)
    public String retrieve(@RequestParam(value = "orderId") String orderId){
        //Service call

        return "index";
    }

    @RequestMapping(value="/retrieve/mock/trips",method = RequestMethod.GET)
    public RetrieveUserResponse mockretrieve(@RequestParam(value = "orderId") String orderId){
        //Service call

        TripDetail tripDetail = new TripDetail();
        tripDetail.setSource("Delhi");
        tripDetail.setSourceCode("DEL");
        tripDetail.setDestination("Dubai");
        tripDetail.setDestinationCode("DUB");
        tripDetail.setCabinType("Economy");
        tripDetail.setFlightNumber("AI-436");
        tripDetail.setIsRefundable(Boolean.TRUE);
        tripDetail.setTravelType("Non-stop");
        tripDetail.setDuration("3");
        tripDetail.setArrivalTime(new DateTime().plusHours(3));
        tripDetail.setDepartureTime(new DateTime());

        TravelerDetail travelerDetails = new TravelerDetail();
        travelerDetails.setName("Test test");
        travelerDetails.setPnr("hgvhjgvhgv5454");
        travelerDetails.setTicketNumber("76576665685");

        RetrieveRateDetails retriveRateDetails = new RetrieveRateDetails();
        retriveRateDetails.setBasefare(Float.valueOf("800"));
        retriveRateDetails.setTaxes(Float.valueOf("200"));
        retriveRateDetails.setTotal(Float.valueOf("10000"));
        retriveRateDetails.setCurrency("EUR");

        RetrieveUserResponse retrieveUserResponse = new RetrieveUserResponse();
        retrieveUserResponse.setTravelerDetails(travelerDetails);
        retrieveUserResponse.setRetriveRateDetails(retriveRateDetails);
        retrieveUserResponse.setFirstLeg(tripDetail);
        retrieveUserResponse.setSecondLeg(tripDetail);

        return retrieveUserResponse;
    }
}
