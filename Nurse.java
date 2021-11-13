package com.company;

public class Nurse extends User{
    private Doctor doctor;

    public Nurse(){};

    public Nurse(String fName, String lName, String bday, String pass, Doctor doc){
        this.firstName = fName;
        this.lastName = lName;
        this.dob = bday;
        this.password = pass;
        this.doctor = doc;
    }

    public Doctor getDoctor(){
        return this.doctor;
    }

    public void setDoctor(Doctor doc){
        this.doctor = doc;
    }

    public Visit createVisit(Patient patient){
        Visit patientVisit = new Visit();
        return patientVisit;
    }
}
