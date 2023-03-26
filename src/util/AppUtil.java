package util;

import java.util.Scanner;

public abstract class AppUtil {
    protected final void menu(){
        this.topMessage("Student Management System");
        System.out.println("1. Insert Student to Application");
        System.out.println("2. Edit Student Information");
        System.out.println("3. Search Student Information");
        System.out.println("4. Delete Student Information");
        System.out.println("5. Show Student Information");
        System.out.println("6. Exit.");
    }

    protected final void searchMenu(){
        System.out.println("1. Search by Id");
        System.out.println("2. Search by Name");
        System.out.println("3. Search by Gender");
        System.out.println("4. Search by Class Name");
        System.out.println("5. Exit");
    }

    public final void topMessage(String message){
        System.out.println("****************** "+ message + " ******************");
    }

    public static void errorWrapper(String message){
        message = "*\tERROR: \t "+ message +", Try again!!! \t*";
        System.out.println("*".repeat(message.length()+3));
        System.out.println(message);
        System.out.println("*".repeat(message.length()+3));
    }

    public final void successMessage(String action){
        action = "*\tSUCCESS: \t You have been "+ action +" successfully. \t*";
        System.out.println("*".repeat(action.length()+7));
        System.out.println(action);
        System.out.println("*".repeat(action.length()+7));
    }

    public void enterContinue(){
        Scanner input = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        input.nextLine();
    }

    public abstract void stop();
}
