package com.nikhilkumar.quakereport;

/**
 * Created by NIKHIL KUMAR on 7/9/2017.
 */

public class Earthquake {
    private double magnitude;
    private String countryplace;
    private long quakedate;
    private String mUrl;

    public Earthquake(double mag, String place, long date ,String url)
    {
         magnitude=mag;
        countryplace=place;
        quakedate=date ;
        mUrl = url;

    }
    public double getMagnitude()
    {
        return  magnitude;

    }
    public String getCountryplace()
    {
        return countryplace;
    }
    public long getQuakedate()
    {
        return quakedate;
    }
    public String getUrl() {
        return mUrl;
    }



}
