package com.ndchack.travelhunt.Util;

/**
 * Created by a-6281 on 2/24/18.
 */
public enum AirlineDetails {
    VANILLA_AIR ("JW", "Vanilla Air");

    String id;
    String name;

    AirlineDetails(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getId() {
        return VANILLA_AIR.id;
    }

    public String getName() {
        return VANILLA_AIR.name;
    }

}
