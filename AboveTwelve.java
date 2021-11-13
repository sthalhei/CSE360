package com.company;

public class AboveTwelve extends Visit{
    private double weight;
    private double height;
    private double temp;
    private double pressure;

    public void setWeight(double newWeight){
        this.weight = newWeight;
    }

    public void setHeight(double newHeight){
        this.height = newHeight;
    }

    public void setTemp(double newTemp){
        this.temp = newTemp;
    }

    public void setPressure(double newPressure){
        this.pressure = newPressure;
    }

    public double getWeight(){
        return this.weight;
    }

    public double getHeight(){
        return this.height;
    }

    public double getTemp(){
        return this.temp;
    }

    public double getPressure(){
        return this.pressure;
    }
}
