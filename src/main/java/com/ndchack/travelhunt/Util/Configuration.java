package com.ndchack.travelhunt.Util;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by a-6281 on 2/24/18.
 */
@Component
public class Configuration {

    public static int discount = 0;
    public static HashMap<String, Float> userSelectedAncillary = new HashMap();
    public static HashMap<String, Float> airlineAncillary = new HashMap();
    public static DateTime returnDepartureTime;
}
