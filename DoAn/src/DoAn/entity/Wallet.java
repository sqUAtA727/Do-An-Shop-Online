package DoAn.entity;

public class Wallet{
    private int accountBalance; //vnd

    public Wallet(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "accountBalance=" + accountBalance +
                '}';
    }
}