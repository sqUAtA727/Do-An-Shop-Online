package DoAn.entity;

import java.math.BigDecimal;

public class Wallet{
    private static int autoID;
    private int id;
    private BigDecimal accountBalance; //vnd

    public Wallet(BigDecimal accountBalance) {
        this.id = ++autoID;
        this.accountBalance = accountBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", accountBalance=" + accountBalance +
                '}';
    }
}