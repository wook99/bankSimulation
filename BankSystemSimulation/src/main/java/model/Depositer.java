package model;

import java.math.BigDecimal;

public class Depositer implements Runnable{

    private BankAccount account;

    private BigDecimal amount;

    public Depositer(BankAccount account, BigDecimal amount) {
        super();
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        try{
            account.deposit(amount);

        }
        catch (Exception e){
            System.out.println("Error in deposit "+e.getMessage());
        }
    }
}
