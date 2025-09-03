package com;
 
import java.time.*;
import java.util.*;
 

class Tap {
    LocalDateTime time;
    String line;
    String station;
    double charged;
 
    Tap(LocalDateTime time, String line, String station, double charged) {
        this.time = time;
        this.line = line;
        this.station = station;
        this.charged = charged;
    }
}
 
interface FareRule { //abstraction
    double apply(Tap previous, Tap current, double fare);
    String getRuleName();
}
 
class PeakFareCalculator implements FareRule { //inheritance
    public double apply(Tap previous, Tap current, double fare) {
        LocalTime t = current.time.toLocalTime();
        if ((t.isAfter(LocalTime.of(7, 59)) && t.isBefore(LocalTime.of(10, 1))) ||
            (t.isAfter(LocalTime.of(17, 59)) && t.isBefore(LocalTime.of(20, 1)))) {
            return fare + 12.5;
        }
        return fare;
    }
    public String getRuleName() {
        return "R2 - Peak Period extra charge rs. 12.5";
    }
}
 
class TransferWindow implements FareRule { //inheritance
    public double apply(Tap previous, Tap current, double fare) {
        if (previous != null) {
            Duration gap = Duration.between(previous.time, current.time);
            if (gap.toMinutes() <= 30) {
                return 0.0;
            }
        }
        return fare;
    }
    public String getRuleName() {
        return "R3 - 30 mins Transfer Window Free Fare";
    }
}
 
class NightDiscount implements FareRule { //inheritance
    public double apply(Tap previous, Tap current, double fare) {
        LocalTime t = current.time.toLocalTime();
        if (t.isAfter(LocalTime.of(21, 59)) && t.isBefore(LocalTime.of(23, 59))) {
            return fare * 0.8;
        }
        if (t.isAfter(LocalTime.MIDNIGHT.minusMinutes(1)) && t.isBefore(LocalTime.of(4, 0))) {
            return fare * 0.65;
        }
        return fare;
    }
    public String getRuleName() {
        return "R4/R5 - Post-Midnight Discount";
    }
}
 
class BaseFareRule implements FareRule { //inheritance
    public double apply(Tap previous, Tap current, double fare) {
        return 25.0;
    }
    public String getRuleName() {
        return "R1 - Base Fare rs. 25";
    }
}
 

class FareEngine {
    List<FareRule> rules;
 
    FareEngine(boolean r1, boolean r2, boolean r3, boolean r4, boolean r5) {
        rules = new ArrayList<>();
        if (r1) {
        	rules.add(new BaseFareRule());
        	}
        if (r2) {
        	rules.add(new PeakFareCalculator());
        }
        if (r3) {
        	rules.add(new TransferWindow());
        }
        if (r4 || r5) {
        	rules.add(new NightDiscount()); 
        }
    }
 
    public double calculateFare(Tap previous, Tap current) {
        double fare = 0.0;
        List<String> appliedRules = new ArrayList<>();
        for (FareRule rule : rules) {
            double newFare = rule.apply(previous, current, fare);
            if (newFare != fare) appliedRules.add(rule.getRuleName());
            fare = newFare;
        }
        System.out.println("Tap at " + current.time + " → rs." + fare + " ==== Rules: " + appliedRules);
        return fare;
    }
}
 
public class tarrif {
    public static void main(String[] args) {
        System.out.println("=====City link fare====== with the rules applied");
 
        String[][] tapData = { // given data
            {"07-01 07:20", "G", "BD", "25"},
            {"07-01 08:01", "G", "NC", "37.5"},
            {"07-01 08:30", "R", "YH", "0"},
            {"07-01 08:32", "Y", "YH", "37.5"},
            {"07-01 10:01", "R", "KL", "25"},
            {"07-01 10:28", "Y", "NC", "0"},
            {"07-01 10:32", "Y", "JT", "25"},
            {"07-01 14:36", "G", "NC", "25"},
            {"07-01 22:15", "Y", "BD", "20"},
            {"07-01 23:58", "G", "NC", "20"},
            {"07-02 00:45", "X", "NC", "16.25"},
            {"07-02 01:10", "G", "BD", "0"},
            {"07-02 04:01", "G", "BD", "25"},
            {"07-02 13:05", "Y", "JT", "25"},
            {"07-02 13:15", "G", "KL", "0"},
            {"07-02 13:36", "G", "JT", "25"},
            {"07-02 18:02", "Y", "BD", "37.5"},
            {"07-02 18:18", "Y", "NC", "0"},
            {"07-02 20:01", "G", "KL", "25"},
            {"07-02 20:15", "R", "YT", "0"},
            {"07-02 22:02", "Y", "KL", "20"},
            {"07-02 23:15", "G", "BD", "20"},
            {"07-03 00:20", "R", "NC", "16.25"}
        };
 
        List<Tap> taps = new ArrayList<>();
        for (String[] row : tapData) {
            String[] dateTime = row[0].split(" ");
            LocalDateTime time = LocalDateTime.of(2025, Integer.parseInt(dateTime[0].split("-")[0]),
                Integer.parseInt(dateTime[0].split("-")[1]),
                Integer.parseInt(dateTime[1].split(":")[0]),
                Integer.parseInt(dateTime[1].split(":")[1]));
            taps.add(new Tap(time, row[1], row[2], Double.parseDouble(row[3])));
        }
 
        FareEngine engine = new FareEngine(true, true, true, true, true);
        Tap previous = null;
        for (Tap current : taps) {
            double fare = engine.calculateFare(previous, current);
            System.out.printf("Expected Fare: rs. %.2f\n", current.charged);
            previous = current;
        }
    }
}