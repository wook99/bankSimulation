package operations;

import model.Bank;

public class IncomeTaxCalculationManager implements Runnable{

    private Bank bank;

    public IncomeTaxCalculationManager(Bank bank) {
        super();
        this.bank = bank;
    }

    @Override
    public void run() {
        try {
            bank.deductIncomeTax();
        }
        catch (Exception e){
            System.err.println("Error occurred in reducing Income Tax "+e.getMessage());
        }
    }
}
