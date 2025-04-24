package com.railway.helloworld.controller.bourseModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Metadata {

    @JsonProperty("2. Symbol")
    private String symbole;

    public String getSymbole() {
        return symbole;
    }

    public void setSymbole(String symbole) {
        this.symbole = symbole;
    }

    @Override
    public String toString() {
        return  "close= " + symbole + '\n';
    }
}
