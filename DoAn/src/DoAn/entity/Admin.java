package DoAn.entity;

public class Admin extends Account{
    public Admin(String username, String email, String password, Wallet wallet, int role) {
        super(username, email, password, wallet, role);
    }
}
