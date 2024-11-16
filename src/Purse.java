import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Purse {
    private final Map<Denomination, Integer> cash;
    private final Denomination[] denominations;
    private final List<PurseObserver> observers;

    public Purse(Denomination[] denominations) {
        cash = new HashMap<>();
        this.denominations = denominations;
        observers = new ArrayList<>();
    }

    // Add observer to the list
    public void addObserver(PurseObserver observer) {
        observers.add(observer);
    }

    // Remove observer from the list
    public void removeObserver(PurseObserver observer) {
        observers.remove(observer);
    }

    // Notify all observers of changes
    private void notifyObservers() {
        for (PurseObserver observer : observers) {
            observer.onPurseChanged(this);
        }
    }

    public void add(Denomination type, int num) {
        if (cash.containsKey(type)) {
            cash.put(type, cash.get(type) + num);
        } else {
            cash.put(type, num);
        }
        notifyObservers();  // Notify observers after adding
    }

    public double remove(Denomination type, int num) {
        if (!cash.containsKey(type)) {
            return 0;
        }

        int currentAmount = cash.get(type);
        if (num > currentAmount) {
            num = currentAmount;
        }

        cash.put(type, currentAmount - num);
        notifyObservers();  // Notify observers after removing
        return type.amt() * num;
    }

    public double getValue() {
        double total = 0.0;
        for (Denomination type : cash.keySet()) {
            total += type.amt() * cash.get(type);
        }
        return total;
    }

    Map<Denomination, Integer> getCash() {
        return cash;
    }

    @Override
    public String toString() {
        if (getValue() == 0) {
            return "empty purse";
        }

        StringBuilder result = new StringBuilder();

        for (Denomination denomination : denominations) {
            if (cash.containsKey(denomination)) {
                result.append(cash.get(denomination)).append(" x ").append(denomination.name()).append("\n");
            }
        }

        result.append("A purse containing ").append(String.format("%.2f", getValue()));

        return result.toString();
    }
}