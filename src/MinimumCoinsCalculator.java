public class MinimumCoinsCalculator extends ChangeCalculator {
    public MinimumCoinsCalculator(Denomination[] denominations) {
        super(denominations);
    }

    @Override
    protected Denomination[] prepareDenominations() {
        // Create a copy of denominations to sort
        Denomination[] sorted = denominations.clone();
        // Sort by amount in descending order
        java.util.Arrays.sort(sorted, (a, b) -> Double.compare(b.amt(), a.amt()));
        return sorted;
    }

    @Override
    protected void calculateDenominations(double amount, Purse purse, Denomination[] denoms) {
        double remaining = amount;
        // Try to use larger denominations first
        for (Denomination denom : denoms) {
            if (remaining >= denom.amt()) {
                int count = (int) (remaining / denom.amt());
                purse.add(denom, count);
                remaining -= count * denom.amt();
            }
        }
    }
}