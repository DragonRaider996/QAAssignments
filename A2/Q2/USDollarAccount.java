public class USDollarAccount extends Account{
    private static final float EXCHANGE_RATE = 0.75f;
    private float USDBalance;

    public void credit(float amount) {
        USDBalance = balance * EXCHANGE_RATE;
        USDBalance += amount * EXCHANGE_RATE;
    }

    public void debit(float amount) {
        USDBalance = balance * EXCHANGE_RATE;
        USDBalance -= amount * EXCHANGE_RATE;
    }

    public float getBalance() {
        return USDBalance/EXCHANGE_RATE;
    }
}