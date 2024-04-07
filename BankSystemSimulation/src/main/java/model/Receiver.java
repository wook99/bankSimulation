package model;


import java.math.BigDecimal;

public class Receiver implements Runnable{

    private Customer customer;
    private BankAccount account;

    private BigDecimal amount;

    public Receiver(Customer customer, BankAccount account, BigDecimal amount) {
        this.customer = customer;
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        //Verify is customer is part of the account
        // if it is true then do the withdrawal
        if(account.isOwner(customer)){
            try {
                account.withdraw(amount);
            }
            catch (Exception e){
                //System.err.println("Error in withdrawal "+e.getMessage());
            }
        }else
            System.err.println("The receiver is not the owner of the account");

    }
}
