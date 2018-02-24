package com.ndchack.travelhunt.ui.domain.retrive;

/**
 * Created by a-6281 on 2/24/18.
 */
public class TravelerDetail {

    //FirstName LastName
    String name;
    String pnr;
    String ticketNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
}
