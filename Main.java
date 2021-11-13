package com.company;

public class Main {

    public static void main(String[] args) {
	String firstName = "Phillip";
    String lastName = "Konstantinov";
    String dob = "01/13/2001";

    User me = new User(firstName, lastName, dob, "topSecret");
    System.out.println(me);

    }
}
