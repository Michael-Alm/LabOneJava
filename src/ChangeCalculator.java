public abstract class ChangeCalculator {
    protected final Denomination[] denominations;

    public ChangeCalculator(Denomination[] denominations) {
        this.denominations = denominations;
    }

    // Template method defining the algorithm structure
    public final Purse calculateChange(double amount) {
        // 1. Initialize the purse
        Purse purse = new Purse(denominations);
        if (amount == 0) {
            return purse;
        }

        // 2. Validate the amount
        if (!validateAmount(amount)) {
            throw new IllegalArgumentException("Invalid amount: " + amount);
        }

        // 3. Sort/filter denominations if needed
        Denomination[] sortedDenoms = prepareDenominations();

        // 4. Calculate the change using the specific strategy
        calculateDenominations(amount, purse, sortedDenoms);

        // 5. Perform post-calculation validation
        if (!validateResult(purse, amount)) {
            throw new IllegalStateException("Change calculation error");
        }

        return purse;
    }

    // These can be overridden by subclasses if needed
    protected boolean validateAmount(double amount) {
        return amount >= 0;
    }

    protected Denomination[] prepareDenominations() {
        return denominations;
    }

    // This must be implemented by concrete classes
    protected abstract void calculateDenominations(double amount, Purse purse, Denomination[] denoms);

    protected boolean validateResult(Purse purse, double targetAmount) {
        return Math.abs(purse.getValue() - targetAmount) < 0.01;
    }
}