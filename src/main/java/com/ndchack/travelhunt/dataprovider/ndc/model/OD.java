package com.ndchack.travelhunt.dataprovider.ndc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A-2174 on 2/24/18.
 */
public class OD {

    private final List<Flight> flights = new ArrayList<>();
    private String referenceKey;

    public List<Flight> getFlights() {
        return flights;
    }

    public String getReferenceKey() {
        return referenceKey;
    }

    public void setReferenceKey(String referenceKey) {
        this.referenceKey = referenceKey;
    }
}
