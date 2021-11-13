package com.company;

public class Authentication {

    public boolean verifyPassword(User user, String passwordInput){
        int counter = 0;
        while(counter < 3) {
            if(user.getPassword().equals(passwordInput)){
                return true;
            }
            else{
                counter++;
                System.out.println("Incorrect password; please try again.");
            }
        }
        return false;

    }
}
