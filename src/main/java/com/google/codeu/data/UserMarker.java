package com.google.codeu.data;


import java.util.HashSet;

public class UserMarker {

    private double lat;
    private double lng;
    private String name;
    private HashSet interest;

    public UserMarker(double lat, double lng, HashSet<String> interest, String Name) {
        this.lat = lat;
        this.lng = lng;
        this.name = Name;
        this.interest = interest;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getName() {
        return name;
    }

    public HashSet getInterest(){
        return interest;
    }


}
