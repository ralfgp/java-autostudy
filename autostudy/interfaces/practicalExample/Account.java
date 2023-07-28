package autostudy.interfaces.practicalExample;

import java.math.BigDecimal;

public class Account implements Withdrawing, Depositing{

    @Override
    public void deposit(BigDecimal amount){}

    @Override
    public BigDecimal withdraw(){ return BigDecimal.ZERO; }
    
}
