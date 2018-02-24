package com.ndchack.travelhunt.dataprovider.ndc.model;

/**
 * Created by A-2174 on 2/25/18.
 */
public class ServiceListRequest {

    private String odId;
    private Flight flight;

    public String getOdId() {
        return odId;
    }

    public void setOdId(String odId) {
        this.odId = odId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
