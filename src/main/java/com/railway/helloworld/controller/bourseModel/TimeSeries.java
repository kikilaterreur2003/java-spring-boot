package com.railway.helloworld.controller.bourseModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeSeries {


    @JsonProperty("3. low")
    private String low;

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }


    @Override
    public String toString() {
        return "low="+low+"\n";
    }
}
