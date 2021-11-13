package com.company;
import java.util.ArrayList;

public class Patient extends User{
    private ArrayList<Visit> visits = new ArrayList<Visit>();
    private ArrayList<String> immunizations = new ArrayList<String>();
    private ArrayList<String> prescriptions = new ArrayList<String>();
    private ArrayList<String> allergies = new ArrayList<String>();
    private ArrayList<String> healthConcerns = new ArrayList<String>();
    private String address;
    private String insurance;
    private String email;
    private String pharmacy;
    private String phone;
    private Doctor assignedDoctor;

    public void requestAppointment(){
        System.out.println("IDK what this is supposed to do");
    }

    public ArrayList<String> getImmunizations(){
        return this.immunizations;
    }

    public String getPatientVisit(Visit visit){
        return visit.toString();
    }

    public void addAllergy(String allergy){
        allergies.add(allergy);
    }

    public void addHealthConcern(String healthConcern){
        healthConcerns.add(healthConcern);
    }

    public void addPrescription(String prescription){
        this.prescriptions.add(prescription);
    }
}
