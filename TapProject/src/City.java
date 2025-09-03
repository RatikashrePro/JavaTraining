import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
 
// Tap class
class Tap {
    public LocalDateTime timestamp;
    public String line;
    public String station;
 
    public Tap(String datetime, String line, String station) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.timestamp = LocalDateTime.parse("2025-" + datetime, formatter);
        this.line = line;
        this.station = station;
    }
}
 
// FareContext class
class FareContext {
    public Tap tap;
    public double fare;
    public Tap lastPaidTap;
    public List<String> appliedRules = new ArrayList<>();
 
    public FareContext(Tap tap, Tap lastPaidTap) {
        this.tap = tap;
        this.lastPaidTap = lastPaidTap;
        this.fare = 0;
    }
 
    public void addRule(String ruleName) {
        appliedRules.add(ruleName);
    }
}
 
// FareRule interface
interface FareRule {
    boolean isEnabled();
    void apply(FareContext context);
    String getName();
}
 
// Base Fare Rule
class BaseFareRule implements FareRule {
    private boolean enabled;
 
    public BaseFareRule(boolean enabled) {
        this.enabled = enabled;
    }
 
    public boolean isEnabled() {
        return enabled;
    }
 
    public void apply(FareContext context) {
        if (enabled) {
            context.fare = 25.0;
            context.addRule(getName());
        }
    }
 
    public String getName() {
        return "Base Fare Rule";
    }
}
 
// Peak Hour Rule
class PeakHourRule implements FareRule {
    private boolean enabled;
 
    public PeakHourRule(boolean enabled) {
        this.enabled = enabled;
    }
 
    public boolean isEnabled() {
        return enabled;
    }
 
    public void apply(FareContext context) {
        LocalTime time = context.tap.timestamp.toLocalTime();
        boolean isPeak = (time.isAfter(LocalTime.of(7, 59)) && time.isBefore(LocalTime.of(10, 1))) ||
                         (time.isAfter(LocalTime.of(17, 59)) && time.isBefore(LocalTime.of(20, 1)));
        if (enabled && isPeak) {
            context.fare *= 1.5;
            context.addRule(getName());
        }
    }
 
    public String getName() {
        return "Peak Hour Surcharge";
    }
}
 
// Transfer Rule
class TransferRule implements FareRule {
    private boolean enabled;
 
    public TransferRule(boolean enabled) {
        this.enabled = enabled;
    }
 
    public boolean isEnabled() {
        return enabled;
    }
 
    public void apply(FareContext context) {
        if (!enabled || context.lastPaidTap == null) return;
 
        Duration diff = Duration.between(context.lastPaidTap.timestamp, context.tap.timestamp);
        if (diff.toMinutes() <= 30) {
            context.fare = 0;
            context.addRule(getName());
        }
    }
 
    public String getName() {
        return "Transfer Window (Free within 30 mins)";
    }
}
 
// Night Discount Rule
class NightDiscountRule implements FareRule {
    private boolean enabled;
 
    public NightDiscountRule(boolean enabled) {
        this.enabled = enabled;
    }
 
    public boolean isEnabled() {
        return enabled;
    }
 
    public void apply(FareContext context) {
        LocalTime time = context.tap.timestamp.toLocalTime();
        if (enabled && time.isAfter(LocalTime.of(21, 59)) && time.isBefore(LocalTime.MIDNIGHT)) {
            context.fare *= 0.8;
            context.addRule(getName());
        }
    }
 
    public String getName() {
        return "Night Discount (20%)";
    }
}
 
// Post-Midnight Discount Rule
class PostMidnightRule implements FareRule {
    private boolean enabled;
 
    public PostMidnightRule(boolean enabled) {
        this.enabled = enabled;
    }
 
    public boolean isEnabled() {
        return enabled;
    }
 
    public void apply(FareContext context) {
        LocalTime time = context.tap.timestamp.toLocalTime();
        if (enabled && time.isAfter(LocalTime.MIDNIGHT) && time.isBefore(LocalTime.of(4, 1))) {
            context.fare *= 0.65;
            context.addRule(getName());
        }
    }
 
    public String getName() {
        return "Post-Midnight Discount (35%)";
    }
}
 
// TariffEngine class
class TariffEngine {
    private List<FareRule> rules;
 
    public TariffEngine(List<FareRule> rules) {
        this.rules = rules;
    }
 
    public FareContext calculateFare(Tap tap, Tap lastPaidTap) {
        FareContext context = new FareContext(tap, lastPaidTap);
        for (FareRule rule : rules) {
            rule.apply(context);
        }
        return context;
    }
}
 
// Main class
public class City {
    public static void main(String[] args) {
        List<FareRule> rules = Arrays.asList(
            new BaseFareRule(true),
            new PeakHourRule(true),
            new TransferRule(true),
            new NightDiscountRule(true),
            new PostMidnightRule(true)
        );
 
        TariffEngine engine = new TariffEngine(rules);
 
        List<Tap> taps = Arrays.asList(
            new Tap("07-01 07:20", "G", "BD"),
            new Tap("07-01 08:01", "G", "NC"),
            new Tap("07-01 08:30", "R", "YH"),
            new Tap("07-01 08:32", "Y", "YH"),
            new Tap("07-01 10:01", "R", "KL"),
            new Tap("07-01 22:15", "Y", "BD"),
            new Tap("07-02 00:45", "X", "NC"),
            new Tap("07-02 01:10", "G", "BD"),
            new Tap("07-02 04:01", "G", "BD"),
            new Tap("07-02 18:02", "Y", "BD"),
            new Tap("07-02 18:18", "Y", "NC"),
            new Tap("07-02 20:01", "G", "KL"),
            new Tap("07-02 22:02", "Y", "KL"),
            new Tap("07-02 23:15", "G", "BD"),
            new Tap("07-03 00:20", "R", "NC")
        );
 
        Tap lastPaidTap = null;
        for (Tap tap : taps) {
            FareContext result = engine.calculateFare(tap, lastPaidTap);
            System.out.printf("Tap at %s Line %s Station %s → ₹%.2f\n",
                tap.timestamp.format(DateTimeFormatter.ofPattern("MM-dd HH:mm")),
                tap.line, tap.station, result.fare);
            System.out.println("  Applied Rules: " + String.join(", ", result.appliedRules));
            if (result.fare > 0) {
                lastPaidTap = tap;
            }
        }
    }
}
 