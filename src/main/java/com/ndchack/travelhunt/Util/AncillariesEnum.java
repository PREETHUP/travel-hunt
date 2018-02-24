package com.ndchack.travelhunt.Util;


public enum AncillariesEnum {

    WIFI("id", ""),
    LEGSPACSE("","");

    String id;
    String name;

    AncillariesEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

}
