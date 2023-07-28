package autostudy.interfaces.practicalExample;

import java.math.BigDecimal;

public class Bank implements Withdrawing, Depositing, Authentication{

    private Account a;
    private Security s;

    @Override
    public void authenticate() {
        s.authenticate();
    }

    @Override
    public void deposit(BigDecimal amount) {
        authenticate();
        a.deposit(amount);
    }

    @Override
    public BigDecimal withdraw() {
        authenticate();
        return a.withdraw();
    }
    
}
