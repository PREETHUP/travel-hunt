package com.ndchack.travelhunt.dataprovider.ndc.model;

/**
 * Created by A-2174 on 2/25/18.
 */
public enum AncillaryService {

    LUGGAGE_20KG("20KG"),
    LUGGAGE_30KG("30KG"),
    LUGGAGE_40KG("40KG"),
    SNACK_BOX("BOX[A-Z]{1}");



    private String code;

    AncillaryService(String code) {
        this.code = code;
    }
}
