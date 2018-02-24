package com.ndchack.travelhunt.dataprovider.ndc.model;

/**
 * Created by A-2174 on 2/25/18.
 */
public class Ancillary {

    private String ancillaryName;
    private String ancillaryCode;
    private Float price;

    public String getAncillaryName() {
        return ancillaryName;
    }

    public void setAncillaryName(String ancillaryName) {
        this.ancillaryName = ancillaryName;
    }

    public String getAncillaryCode() {
        return ancillaryCode;
    }

    public void setAncillaryCode(String ancillaryCode) {
        this.ancillaryCode = ancillaryCode;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
