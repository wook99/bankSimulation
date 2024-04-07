package operations;

import model.Bank;

public class AnnualChargesCalculationManager implements Runnable{

    private Bank bank;

    public AnnualChargesCalculationManager(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        try {
            bank.deductAnnualCharges();
        }
        catch (Exception e){
            System.err.println("Error occurred in reducing annual charges "+e.getMessage());
        }
    }
}
