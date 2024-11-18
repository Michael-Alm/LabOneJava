public class StandardChangeCalculator extends ChangeCalculator {
    public StandardChangeCalculator(Denomination[] denominations) {
        super(denominations);
    }

    @Override
    protected void calculateDenominations(double amount, Purse purse, Denomination[] denoms) {
        double remaining = amount;
        for (Denomination denom : denoms) {
            int count = (int) (remaining / denom.amt());
            if (count > 0) {
                purse.add(denom, count);
                remaining -= count * denom.amt();
            }
        }
    }
}