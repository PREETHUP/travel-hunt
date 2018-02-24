package com.ndchack.travelhunt.dataprovider.ndc.model;

import org.iata.iata.edist.OrderIDType;
import org.iata.iata.edist.OrderRetrieveRQ;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A-2174 on 2/24/18.
 */
public class OrderView {


    private final List<OD> ods = new ArrayList<>();
    private final List<Passenger> pax = new ArrayList<>();

    private Fare fare;

    public List<OD> getOds() {
        return ods;
    }

    public List<Passenger> getPax() {
        return pax;
    }

    public Fare getFare() {
        return fare;
    }

    public void setFare(Fare fare) {
        this.fare = fare;
    }
}
