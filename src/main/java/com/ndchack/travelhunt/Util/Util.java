package com.ndchack.travelhunt.Util;

import java.math.BigDecimal;

/**
 * Created by a-6281 on 2/25/18.
 */
public class Util {

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}
