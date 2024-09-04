import java.util.Scanner;

public class Register {
    // Use an array to hold the denominations in descending order
    private final Denomination[] denominations;

    public Register() {
        // Initialize the array of denominations in descending order
        denominations = new Denomination[]{
                new Denomination("One Hundred Dollar Note", 100.0, "bill", "100_dollar_bill.png"),
                new Denomination("Fifty-Dollar Note", 50.0, "bill", "50_dollar_bill.png"),
                new Denomination("Twenty-Dollar Note", 20.0, "bill", "20_dollar_bill.png"),
                new Denomination("Ten-Dollar Note", 10.0, "bill", "10_dollar_bill.png"),
                new Denomination("Five-Dollar Note", 5.0, "bill", "5_dollar_bill.png"),
                new Denomination("One-Dollar Note", 1.0, "bill", "1_dollar_bill.png"),
                new Denomination("Quarter", 0.25, "coin", "25_cent.png"),
                new Denomination("Dime", 0.10, "coin", "10_cent.png"),
                new Denomination("Nickel", 0.05, "coin", "05_cent.png"),
                new Denomination("Penny", 0.01, "coin", "01_cent.png")
        };
    }

    // Create a purse that makes change for a given amount
    public Purse makeChange(double amt) {
        // Create a purse object with the array of denominations
        Purse purse = new Purse(denominations);
        if (amt == 0) {
            // If amount is 0, return the purse as empty
            return purse;
        }

        // Loop through the denominations in descending order
        for (Denomination denom : denominations) {
            // Determine how many of this denomination can be used for the amount
            int count = (int) (amt / denom.amt());
            if (count > 0) {
                // Add the denomination to the purse and subtract its value
                purse.add(denom, count);
                amt -= count * denom.amt();
            }
        }
        return purse;
    }

    // Getter for the denominations array
    public Denomination[] getDenominations() {
        return denominations;
    }

    public static void main(String[] args) {
        // Create the register object
        Register register = new Register();

        // Create the scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Ask the user to input the amount for making change
        System.out.print("Please enter the amount in the purse: ");
        double amount = scanner.nextDouble();

        // Generate and display the purse with the change
        Purse purse = register.makeChange(amount);
        System.out.println(purse);

        // Close the scanner
        scanner.close();
    }
}
