/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
/*
  Mostakim Al Billah Siam
  ID: 0242310005341314
*/

package com.mycompany.farmfresh_dairy;

/**
 *
 * @author an
 */
import java.util.*;
import java.text.*;

class Cow {
    String id;
    String breed;
    int age;
    double milkYield; 
    String isHealthy;
    String isLactating;

    public Cow(String id, String breed, int age, double milkYield, String isHealthy, String isLactating) {
        this.id = id;
        this.breed = breed;
        this.age = age;
        this.milkYield = milkYield;
        this.isHealthy = isHealthy;
        this.isLactating = isLactating;
    }

    public void updateMilkYield(double yield) {
        this.milkYield = yield;
    }

    public void updateHealthStatus(String status) {
        this.isHealthy = status;
    }

    public String toString() {
        return "Cow ID: " + id + ", Breed: " + breed + ", Age: " + age + ", Milk Yield: " + milkYield + "L, Healthy: " + isHealthy + ", Lactating: " + isLactating;
    }
}

class MilkProduction {
    String cowId;
    double dailyYield;
    String date;

    public MilkProduction(String cowId, double dailyYield, String date) {
        this.cowId = cowId;
        this.dailyYield = dailyYield;
        this.date = date;
    }

    public String toString() {
        return "Cow ID: " + cowId + ", Daily Yield: " + dailyYield + "L, Date: " + date;
    }
}

class FeedingSchedule {
    String cowId;
    String feedType;
    double quantity; 
    String date;

    public FeedingSchedule(String cowId, String feedType, double quantity, String date) {
        this.cowId = cowId;
        this.feedType = feedType;
        this.quantity = quantity;
        this.date = date;
    }

    public String toString() {
        return "Cow ID: " + cowId + ", Feed Type: " + feedType + ", Quantity: " + quantity + "kg, Date: " + date;
    }
}

class FarmFinancials {
    double totalIncome;
    double totalExpenses;

    public void recordIncome(double amount) {
        totalIncome += amount;
    }

    public void recordExpense(double amount) {
        totalExpenses += amount;
    }

    public double getProfit() {
        return totalIncome - totalExpenses;
    }

    public String toString() {
        return "Total Income: " + totalIncome + " BDT, Total Expenses: " + totalExpenses + " BDT, Profit: " + getProfit() + " BDT";
    }
}

public class FarmFresh_Dairy {
    private static Scanner sc = new Scanner(System.in);
    private static Map<String, Cow> cows = new HashMap<>();
    private static List<MilkProduction> milkProductions = new ArrayList<>();
    private static List<FeedingSchedule> feedingSchedules = new ArrayList<>();
    private static FarmFinancials farmFinancials = new FarmFinancials();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n<----- FarmFresh Dairy ----->");
            System.out.println("1. Add New Cow");
            System.out.println("2. Update Milk Yield");
            System.out.println("3. Record Feeding Schedule");
            System.out.println("4. Record Milk Production");
            System.out.println("5. View Cows");
            System.out.println("6. View Milk Production");
            System.out.println("7. View Feeding Schedules");
            System.out.println("8. View Financials");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 
            
            switch (choice) {
                case 1: addNewCow(); break;
                case 2: updateMilkYield(); break;
                case 3: recordFeedingSchedule(); break;
                case 4: recordMilkProduction(); break;
                case 5: viewCows(); break;
                case 6: viewMilkProduction(); break;
                case 7: viewFeedingSchedules(); break;
                case 8: viewFinancials(); break;
                case 9: System.out.println("Exiting system..."); break;
                default: System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 9);
    }

    public static void addNewCow() {
        System.out.print("Enter Cow ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Breed: ");
        String breed = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        System.out.print("Enter Milk Yield: ");
        double milkYield = sc.nextDouble();
        sc.nextLine(); 
        System.out.print("Is the cow healthy (Yes/No)? ");
        String isHealthy = sc.nextLine();
        System.out.print("Is the cow lactating (Yes/No)? ");
        String isLactating = sc.nextLine();
        
        Cow newCow = new Cow(id, breed, age, milkYield, isHealthy, isLactating);
        cows.put(id, newCow);
        System.out.println("--> Cow added successfully!");
    }

    public static void updateMilkYield() {
        System.out.print("Enter Cow ID: ");
        String id = sc.nextLine();
        if (cows.containsKey(id)) {
            System.out.print("Enter new Milk Yield (liters): ");
            double yield = sc.nextDouble();
            cows.get(id).updateMilkYield(yield);
            System.out.println("Milk yield updated successfully!");
        } else {
            System.out.println("Cow not found!");
        }
    }

    public static void recordFeedingSchedule() {
        System.out.print("Enter Cow ID: ");
        String cowId = sc.nextLine();
        System.out.print("Enter Feed Type: ");
        String feedType = sc.nextLine();
        System.out.print("Enter Quantity (kg): ");
        double quantity = sc.nextDouble();
        sc.nextLine(); 
        System.out.print("Enter Date (dd/MM/yyyy): ");
        String date = sc.nextLine();

        FeedingSchedule feeding = new FeedingSchedule(cowId, feedType, quantity, date);
        feedingSchedules.add(feeding);
        System.out.println("Feeding schedule recorded!");
    }

    public static void recordMilkProduction() {
        System.out.print("Enter Cow ID: ");
        String cowId = sc.nextLine();
        if (cows.containsKey(cowId)) {
            System.out.print("Enter Milk Yield (liters): ");
            double yield = sc.nextDouble();
            sc.nextLine(); 
            System.out.print("Enter Date (dd/MM/yyyy): ");
            String date = sc.nextLine();

            MilkProduction production = new MilkProduction(cowId, yield, date);
            milkProductions.add(production);
            farmFinancials.recordIncome(yield * 40);  
            System.out.println("Milk production recorded!");
        } else {
            System.out.println("Cow not found!");
        }
    }

    public static void viewCows() {
        if (cows.isEmpty()) {
            System.out.println("No cows registered!");
        } else {
            for (Cow cow : cows.values()) {
                System.out.println(cow);
            }
        }
    }

    public static void viewMilkProduction() {
        if (milkProductions.isEmpty()) {
            System.out.println("No milk production records!");
        } else {
            for (MilkProduction production : milkProductions) {
                System.out.println(production);
            }
        }
    }

    public static void viewFeedingSchedules() {
        if (feedingSchedules.isEmpty()) {
            System.out.println("No feeding schedules recorded!");
        } else {
            for (FeedingSchedule schedule : feedingSchedules) {
                System.out.println(schedule);
            }
        }
    }

    public static void viewFinancials() {
        System.out.println(farmFinancials);
    }
}

