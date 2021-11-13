package com.company;

import java.util.ArrayList;
import java.util.Date;

public class Visit {
    protected ArrayList<String> allergies = new ArrayList<String>();
    protected  ArrayList<String> healthConcerns = new ArrayList<String>();
    protected ArrayList<String> prescriptions = new ArrayList<String>();
    protected String notes;
    protected String summary;
    protected  Date date;
    protected Patient patient;

    public Visit(){};

    public void addAllergies(String allergy){
        allergies.add(allergy);
        this.patient.addAllergy(allergy);
    }

    public void addHealthConcern(String healthConcern){
        healthConcerns.add(healthConcern);
        this.patient.addHealthConcern(healthConcern);
    }

    public ArrayList<String> getHealthConcerns(){
        return this.healthConcerns;
    }

    public void addPrescription(String prescription){
        prescriptions.add(prescription);
        this.patient.addPrescription(prescription);
    }

    public void sendPrescription(String pharmacy, String prescription){
        System.out.printf("The prescription, %S, has been sent to %s", prescription, pharmacy);
    }


}
