package com.ndchack.travelhunt.ui.domain.retrive;


import java.util.List;

/**
 * Created by a-6281 on 2/24/18.
 */
public class RetrieveUserResponse {

    TravelerDetails travelerDetails;
    TripDetails firstLeg;
    TripDetails secondLeg;
    RetrieveRateDetails retriveRateDetails;

    public TravelerDetails getTravelerDetails() {
        return travelerDetails;
    }

    public void setTravelerDetails(TravelerDetails travelerDetails) {
        this.travelerDetails = travelerDetails;
    }

    public TripDetails getFirstLeg() {
        return firstLeg;
    }

    public void setFirstLeg(TripDetails firstLeg) {
        this.firstLeg = firstLeg;
    }

    public TripDetails getSecondLeg() {
        return secondLeg;
    }

    public void setSecondLeg(TripDetails secondLeg) {
        this.secondLeg = secondLeg;
    }

    public RetrieveRateDetails getRetriveRateDetails() {
        return retriveRateDetails;
    }

    public void setRetriveRateDetails(RetrieveRateDetails retriveRateDetails) {
        this.retriveRateDetails = retriveRateDetails;
    }
}
