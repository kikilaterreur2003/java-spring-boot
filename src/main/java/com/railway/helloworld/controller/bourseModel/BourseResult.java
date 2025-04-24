package com.railway.helloworld.controller.bourseModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class BourseResult {

    @JsonProperty("Meta Data")
    private Metadata metadata;
    @JsonProperty("Time Series (5min)")
    private Map<String, TimeSeries> data;

    public Map<String, TimeSeries> getData() {
        return data;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public void setData(Map<String, TimeSeries> data) {
        this.data = data;
    }

    @Override
    public String toString(){
        return "["+ metadata.toString()+data.toString()+"]";
    }
}
