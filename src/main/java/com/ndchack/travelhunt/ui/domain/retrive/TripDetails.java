package com.ndchack.travelhunt.ui.domain.retrive;

import org.joda.time.DateTime;

/**
 * Created by a-6281 on 2/24/18.
 */
public class TripDetails {

    String source;
    String sourceCode;

    String destination;
    String destinationCode;

    //carrierCode+Flight number AI-436
    String flightNumber;

    //Economy
    String cabinType;
    String isRefundable;
    //Non-stop
    String type;

    String duration;
    DateTime arrivalTime;
    DateTime departureTime;
}
