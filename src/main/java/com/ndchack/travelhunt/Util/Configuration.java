package com.ndchack.travelhunt.Util;

import org.joda.time.DateTime;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by a-6281 on 2/24/18.
 */
@Component
public class Configuration {

    public static int discount = 75;
    public static HashMap<String, Float> userSelectedAncillary = new HashMap();
    public static HashMap<String, Float> airlineAncillary = new HashMap();
    public static DateTime returnDepartureTime;
    public static DateTime departureTime;

/*    public void afterPropertiesSet() {
        airlineAncillary.put("Extra Leg Room", Float.valueOf("40"));
        airlineAncillary.put("Primary boarding", Float.valueOf("20"));
        airlineAncillary.put("Wifi Internet", Float.valueOf("10"));

        userSelectedAncillary.put("Primary boarding", Float.valueOf("20"));
        userSelectedAncillary.put("Wifi Internet", Float.valueOf("10"));

        returnDepartureTime = new DateTime("2018-03-01T00:00");
        departureTime = new DateTime("2018-02-24T17:22");
    }*/
}
