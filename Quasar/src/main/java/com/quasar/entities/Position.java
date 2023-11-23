package com.quasar.entities;

public class Position {

    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Position(double[] xy) {
        //Rounding to 2 decimal
        this.x = (double) Math.round(xy[0]*10)/10;
        this.y = (double) Math.round(xy[1]*10)/10;
    }

    public double[] asArray() {
        double[] position = {this.x, this.y};
        return position;
    }
}
