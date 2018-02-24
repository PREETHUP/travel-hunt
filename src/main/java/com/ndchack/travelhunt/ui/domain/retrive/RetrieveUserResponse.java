package com.ndchack.travelhunt.ui.domain.retrive;


/**
 * Created by a-6281 on 2/24/18.
 */
public class RetrieveUserResponse {

    TravelerDetail travelerDetails;
    TripDetail firstLeg;
    TripDetail secondLeg;
    RetrieveRateDetails retriveRateDetails;

    public TravelerDetail getTravelerDetails() {
        return travelerDetails;
    }

    public void setTravelerDetails(TravelerDetail travelerDetails) {
        this.travelerDetails = travelerDetails;
    }

    public TripDetail getFirstLeg() {
        return firstLeg;
    }

    public void setFirstLeg(TripDetail firstLeg) {
        this.firstLeg = firstLeg;
    }

    public TripDetail getSecondLeg() {
        return secondLeg;
    }

    public void setSecondLeg(TripDetail secondLeg) {
        this.secondLeg = secondLeg;
    }

    public RetrieveRateDetails getRetriveRateDetails() {
        return retriveRateDetails;
    }

    public void setRetriveRateDetails(RetrieveRateDetails retriveRateDetails) {
        this.retriveRateDetails = retriveRateDetails;
    }
}
