package com.ndchack.travelhunt.controller;

import com.ndchack.travelhunt.Util.Configuration;
import com.ndchack.travelhunt.Util.OrderResponseData;
import com.ndchack.travelhunt.dataprovider.ndc.model.Flight;
import com.ndchack.travelhunt.dataprovider.ndc.model.OrderView;
import com.ndchack.travelhunt.dataprovider.ndc.service.GetOrderService;
import com.ndchack.travelhunt.ui.domain.retrive.RetrieveRateDetails;
import com.ndchack.travelhunt.ui.domain.retrive.RetrieveUserResponse;
import com.ndchack.travelhunt.ui.domain.retrive.TravelerDetail;
import com.ndchack.travelhunt.ui.domain.retrive.TripDetail;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.web.bind.annotation.*;

/**
 * Created by a-6281 on 2/24/18.
 */
@RestController
public class RetrieveController {

    private OrderView orderView;

    public GetOrderService service;

    @RequestMapping(value = "/retrieve/trips", method = RequestMethod.GET)
    public RetrieveUserResponse retrieve(@RequestParam(value = "orderId", defaultValue = "R4254R", required = false) String orderId) {
        service = new GetOrderService();
        //Service call
        orderView = service.getOrder(orderId);
        OrderResponseData.setOrderDetails(orderView);
        TravelerDetail travelerDetails = new TravelerDetail();
        travelerDetails.setName(orderView.getPax().get(0).getPassengerName());
        travelerDetails.setPnr(orderId);
        travelerDetails.setTicketNumber(orderView.getOds().get(0).getReferenceKey()); // no ticket number found

        RetrieveRateDetails retriveRateDetails = new RetrieveRateDetails();
        retriveRateDetails.setBasefare(orderView.getFare().getBaseFare());
        /*retriveRateDetails.setTaxes(Float.valueOf("200")); only base fare is available
        retriveRateDetails.setTotal(Float.valueOf("10000"));*/
        retriveRateDetails.setCurrency("EUR");

        RetrieveUserResponse retrieveUserResponse = new RetrieveUserResponse();
        retrieveUserResponse.setTravelerDetails(travelerDetails);
        retrieveUserResponse.setRetriveRateDetails(retriveRateDetails);
        retrieveUserResponse.setFirstLeg(setFlightDetails(orderView.getOds().get(0).getFlights().get(0), Boolean.TRUE));
        retrieveUserResponse.setSecondLeg(setFlightDetails(orderView.getOds().get(1).getFlights().get(0), Boolean.FALSE));

        return retrieveUserResponse;
    }

    @RequestMapping(value = "/retrieve/mock/trips", method = RequestMethod.GET)
    public RetrieveUserResponse mockretrieve(@RequestParam(value = "orderId") String orderId) {
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
        tripDetail.setArrivalTime(new DateTime().toDate());
        tripDetail.setDepartureTime(new DateTime().toDate());

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

    private TripDetail setFlightDetails(Flight flightDetail, Boolean isFirstLeg) {

        TripDetail legDetail = new TripDetail();
        legDetail.setSource(flightDetail.getDepartureAirport());
        legDetail.setSourceCode(flightDetail.getDepartureAirport());// No Code available
        legDetail.setDestination(flightDetail.getArrivalAirport());
        legDetail.setDestinationCode(flightDetail.getArrivalAirport());// No Code available
        legDetail.setCabinType(flightDetail.getCabinClass());
        legDetail.setCarrier(flightDetail.getMarketingCarrier());
        legDetail.setFlightNumber(flightDetail.getFlightNumber());
        legDetail.setIsRefundable(Boolean.TRUE); // No refundable details available
        legDetail.setTravelType(getTravelType(flightDetail.getNumberOfStops())); //only count is got.

        DateTime arrivalTime = flightDetail.getArrivalTime();
        DateTime departureTime = flightDetail.getDepartureTime();

        legDetail.setDuration(getDuration(departureTime, arrivalTime)); //departure time - arrival time
        legDetail.setArrivalTime(arrivalTime.toDate());
        legDetail.setDepartureTime(departureTime.toDate());

        if (isFirstLeg) {
            Configuration.firstLegDepartureTime = departureTime;
        } else {
            Configuration.returnDepartureTime = departureTime;
        }
        return legDetail;
    }

    private String getTravelType(int numberOfStop) {
        return numberOfStop == 1 ? "NonStop" : "Stop"; //Stop should show number of stop
    }

    private String getDuration(DateTime departureTime, DateTime arrivalTime) {
        return String.valueOf(new Period(departureTime, arrivalTime).getHours());
    }

}
