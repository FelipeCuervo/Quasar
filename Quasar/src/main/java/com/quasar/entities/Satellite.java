package com.quasar.entities;

import java.util.Arrays;

public class Satellite {

    private String name;
    private double distance;
    private String[] message;

    public Satellite() {

    }

    public Satellite(String satelliteName) {
    }

    public Satellite(double distance, String[] message) {
        this.distance=distance;
        this.message=message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String[] getMessage() {
        return message;
    }

    public void setMessage(String[] message) {
        this.message = message;
    }

    public Satellite(String name, double distance, String[] message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }

    @Override
    public String toString() {
        return "{\n" +

                " distance=" + distance +
                ", \n message=" + Arrays.toString(message) +
                "\n}";
    }
}
