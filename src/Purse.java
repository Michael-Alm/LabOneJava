import java.util.HashMap;
import java.util.Map;

public class Purse {
    private final Map<Denomination, Integer> cash; // Stores denominations and their counts
    private final Denomination[] denominations; // Reference to the array from Register

    // Constructor that takes the denominations array from Register
    public Purse(Denomination[] denominations) {
        cash = new HashMap<>();
        this.denominations = denominations; // Store the passed array
    }

    // Add a specific denomination to the purse
    public void add(Denomination type, int num) {
        if (cash.containsKey(type)) {
            // If the denomination is already in the purse, increment its count
            cash.put(type, cash.get(type) + num);
        } else {
            // Otherwise, add the new denomination
            cash.put(type, num);
        }
    }

    // Remove a specific denomination from the purse and return the total value removed
    public double remove(Denomination type, int num) {
        if (!cash.containsKey(type)) {
            return 0; // No denomination to remove
        }

        int currentAmount = cash.get(type);
        if (num > currentAmount) {
            // If trying to remove more than what's available, limit to available amount
            num = currentAmount;
        }

        // Subtract the specified number of bills/coins and return the value removed
        cash.put(type, currentAmount - num);
        return type.amt() * num; // Multiply the denomination value by the number removed
    }

    // Calculate the total value of money in the purse
    public double getValue() {
        double total = 0.0;
        // Loop through each denomination and calculate its total value
        for (Denomination type : cash.keySet()) {
            total += type.amt() * cash.get(type); // Multiply amount by count
        }
        return total;
    }

    Map<Denomination, Integer> getCash() {
        return cash;
    }

    // Return a string representation of the purse contents
    @Override
    public String toString() {
        // If purse is empty, return "empty purse"
        if (getValue() == 0) {
            return "empty purse";
        }

        StringBuilder result = new StringBuilder();

        // Iterate over the denominations array passed from Register
        for (Denomination denomination : denominations) {
            if (cash.containsKey(denomination)) {
                // Add the denomination and its count to the result string
                result.append(cash.get(denomination)).append(" x ").append(denomination.name()).append("\n");
            }
        }

        // Append the total value of the purse
        result.append("A purse containing ").append(String.format("%.2f", getValue()));

        return result.toString();
    }
}
