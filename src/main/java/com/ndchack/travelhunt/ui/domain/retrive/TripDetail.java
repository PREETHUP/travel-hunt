package com.ndchack.travelhunt.ui.domain.retrive;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by a-6281 on 2/24/18.
 */
public class TripDetail {

    String source;
    String sourceCode;

    String destination;
    String destinationCode;

    //carrierCode+Flight number AI-436
    String carrier;
    String flightNumber;

    //Economy
    String cabinType;
    Boolean isRefundable;
    //Non-stop
    String travelType;

    String duration;
    Date arrivalTime;
    Date departureTime;

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public Boolean getRefundable() {
        return isRefundable;
    }

    public void setRefundable(Boolean refundable) {
        isRefundable = refundable;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getCabinType() {
        return cabinType;
    }

    public void setCabinType(String cabinType) {
        this.cabinType = cabinType;
    }

    public Boolean getIsRefundable() {
        return isRefundable;
    }

    public void setIsRefundable(Boolean isRefundable) {
        this.isRefundable = isRefundable;
    }

    public String getTravelType() {
        return travelType;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }
}
