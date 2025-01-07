package DoAn.entity;

import java.math.BigDecimal;

public class Staff extends Account {
    private BigDecimal salary;
    private String schedule; //Theo lịch trong tuần tức là từ T2 đến CN

    public Staff(String username, String email, String password, Wallet wallet, int role, BigDecimal salary, String schedule) {
        super(username, email, password, wallet, role);
        this.salary = salary;
        this.schedule = schedule;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", role=" + getRole() +
                ", wallet=" + getWallet() +
                ", salary=" + salary +
                ", schedule='" + schedule + '\'' +
                '}';
    }
}