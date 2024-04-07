package operations;

import model.Bank;

public class InterestCalculationManager implements Runnable{

    private Bank bank;

    public InterestCalculationManager(Bank bank) {
        super();
        this.bank = bank;
    }

    @Override
    public void run() {
        try {
            bank.addMonthlyInterest();
        }
        catch (Exception e){
            System.err.println("Error occurred in adding monthly interest "+e.getMessage());
        }
    }
}
