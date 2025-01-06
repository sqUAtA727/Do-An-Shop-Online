package DoAn.entity;

public class Staff extends Account {
    private int salary;
    private String schedule; //Theo lịch trong tuần tức là từ T2 đến CN

    public Staff(String username, String email, String password, int role, int salary, String schedule) {
        super(username, email, password, role);
        this.salary = salary;
        this.schedule = schedule;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
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
                "salary=" + salary +
                ", schedule='" + schedule + '\'' +
                '}';
    }
}