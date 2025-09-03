package com;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
 
// Tap class
class Tap {
    LocalDateTime time;
    String line;
    String station;
 
    Tap(LocalDateTime time, String line, String station) {
        this.time = time;
        this.line = line;
        this.station = station;
    }
}
 
// Context class to hold history and rule toggles
class Context {
    List<Tap> history = new ArrayList<>();
    Map<String, Boolean> ruleToggles = new HashMap<>();
 
    boolean isRuleEnabled(String ruleName) {
        return ruleToggles.getOrDefault(ruleName, false);
    }
}
 
// FareRule base class
abstract class FareRule {
    abstract double apply(Tap tap, Context context);
}
 
// Rule R1: Base Fare
class BaseFareRule extends FareRule {
    public double apply(Tap tap, Context context) {
        return context.isRuleEnabled("R1") ? 25.0 : 0.0;
    }
}
 
// Rule R2: Peak Surcharge
class PeakSurchargeRule extends FareRule {
    public double apply(Tap tap, Context context) {
        if (!context.isRuleEnabled("R2")) return 0.0;
        int hour = tap.time.getHour();
        if ((hour >= 8 && hour < 10) || (hour >= 18 && hour < 20)) {
            return 12.5; // 50% surcharge
        }
        return 0.0;
    }
}
 
// Rule R3: Transfer Window
class TransferWindowRule extends FareRule {
    public double apply(Tap tap, Context context) {
        if (!context.isRuleEnabled("R3")) return 0.0;
        for (int i = context.history.size() - 1; i >= 0; i--) {
            Tap prev = context.history.get(i);
            Duration diff = Duration.between(prev.time, tap.time);
            if (diff.toMinutes() <= 30) return -25.0; // negate base fare
        }
        return 0.0;
    }
}
 
// Rule R4: Night Discount
class NightDiscountRule extends FareRule {
    public double apply(Tap tap, Context context) {
        if (!context.isRuleEnabled("R4")) return 0.0;
        int hour = tap.time.getHour();
        if (hour >= 22 && hour < 24) return -5.0; // 20% discount
        return 0.0;
    }
}
 
// Rule R5: Post-Midnight Discount
class PostMidnightDiscountRule extends FareRule {
    public double apply(Tap tap, Context context) {
        if (!context.isRuleEnabled("R5")) return 0.0;
        int hour = tap.time.getHour();
        if (hour >= 0 && hour < 4) return -8.75; // 35% discount
        return 0.0;
    }
}
 
// TariffEngine class
class TariffEngine {
    List<FareRule> rules;
 
    TariffEngine(List<FareRule> rules) {
        this.rules = rules;
    }
 
    double computeFare(Tap tap, Context context) {
        double fare = 0.0;
        for (FareRule rule : rules) {
            fare += rule.apply(tap, context);
        }
        context.history.add(tap);
        return Math.max(fare, 0.0); // no negative fares
    }
}
 
// Main class
public class City {
    public static void main(String[] args) {
        List<FareRule> rules = Arrays.asList(
            new BaseFareRule(),
            new PeakSurchargeRule(),
            new TransferWindowRule(),
            new NightDiscountRule(),
            new PostMidnightDiscountRule()
        );
 
        TariffEngine engine = new TariffEngine(rules);
        Context context = new Context();
 
        // Enable all rules
        context.ruleToggles.put("R1", true);
        context.ruleToggles.put("R2", true);
        context.ruleToggles.put("R3", true);
        context.ruleToggles.put("R4", true);
        context.ruleToggles.put("R5", true);
 
        // Sample tap data
        String[][] rawTaps = {
            {"07-01 07:20", "G", "BD"},
            {"07-01 08:01", "G", "NC"},
            {"07-01 08:30", "R", "YH"},
            {"07-01 08:32", "Y", "YH"},
            {"07-01 10:01", "R", "KL"},
            {"07-01 10:28", "Y", "NC"},
            {"07-01 10:32", "Y", "JT"},
            {"07-01 14:36", "G", "NC"},
            {"07-01 22:15", "Y", "BD"},
            {"07-01 23:58", "G", "NC"},
            {"07-02 00:45", "X", "NC"},
            {"07-02 01:10", "G", "BD"},
            {"07-02 04:01", "G", "BD"},
            {"07-02 13:05", "Y", "JT"},
            {"07-02 13:15", "G", "KL"},
            {"07-02 13:36", "G", "JT"},
            {"07-02 18:02", "Y", "BD"},
            {"07-02 18:18", "Y", "NC"},
            {"07-02 20:01", "G", "KL"},
            {"07-02 20:15", "R", "YT"},
            {"07-02 22:02", "Y", "KL"},
            {"07-02 23:15", "G", "BD"},
            {"07-03 00:20", "R", "NC"}
        };
 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
 
        for (String[] rawTap : rawTaps) {
            String dateTimeStr = "2025-" + rawTap[0]; // prepend year
            LocalDateTime time = LocalDateTime.parse(dateTimeStr, formatter);
            Tap tap = new Tap(time, rawTap[1], rawTap[2]);
            double fare = engine.computeFare(tap, context);
            System.out.printf("Tap at %s Line %s Station %s → ₹%.2f%n", rawTap[0], rawTap[1], rawTap[2], fare);
        }
    }
}