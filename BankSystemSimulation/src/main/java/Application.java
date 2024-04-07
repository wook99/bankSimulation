import accounts.CurrentBankAccount;
import accounts.SavingsBankAccount;
import model.Bank;
import model.BankAccount;
import model.Customer;
import model.Depositer;
import model.Receiver;
import operations.AnnualChargesCalculationManager;
import operations.IncomeTaxCalculationManager;
import operations.InterestCalculationManager;
import util.AccountType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static util.AccountType.REGULAR;

public class Application {

    public static void main(String[] args) {
        Bank bank = new Bank();

        Random random = new Random();

        List<Customer> customers = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        List<BankAccount> bankAccounts = new ArrayList<>();
        List<Thread> depositorThread = new ArrayList<>();
        List<Thread> receiverThread = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            customers.add(new Customer("C0"+i,"Tharindi","Hansika","0704339562","address","123456"));
            bankAccounts.add(new SavingsBankAccount("SavingsAccount : 0"+i, customers, i%2 ==0 ? AccountType.VIP: REGULAR));
            bankAccounts.add(new CurrentBankAccount("CurrentAccount : 0"+i, customers, i%2 ==0 ? AccountType.VIP: REGULAR));
        }


        ThreadGroup bankManager = new ThreadGroup("BankManager");
        ThreadGroup customerVIP = new ThreadGroup("VIP Customer");
        ThreadGroup regularCustomer = new ThreadGroup("Regular Customer");
        customerVIP.setMaxPriority(8);
        regularCustomer.setMaxPriority(5);
        bankManager.setMaxPriority(10);

        for (BankAccount bankAccount: bankAccounts){
            bank.addBankAccount(bankAccount);
        }

        // create array of threads
        Thread annualChargersCalculatormanagerThread = new Thread(bankManager,new AnnualChargesCalculationManager(bank),"Annual Chargers calculation manager");
        Thread incomeTaxCalculatormanagerThread = new Thread(bankManager,new IncomeTaxCalculationManager(bank),"Income Tax calculation manager");
        Thread interestCalculatormanagerThread = new Thread(bankManager,new InterestCalculationManager(bank),"Interest calculation manager");

        for (int i = 0; i < 20; i++) {
            depositorThread.add(new Thread(bankAccounts.get(i).getAccountType().equals(REGULAR)? regularCustomer : customerVIP ,new Depositer(bankAccounts.get(i),BigDecimal.valueOf(random.nextInt(50000))),"Depositor: "+i));
        }

        for (int i = 0; i < 20; i++) {
            receiverThread.add(new Thread(bankAccounts.get(i).getAccountType().equals(REGULAR)? regularCustomer : customerVIP ,new Receiver(customers.get(i),bankAccounts.get(i),BigDecimal.valueOf(random.nextInt(50000))),"Receiver: "+i));

        }

        threads.add(annualChargersCalculatormanagerThread);
        threads.add(incomeTaxCalculatormanagerThread);
        threads.add(interestCalculatormanagerThread);

        for(Thread depositor : depositorThread){
            depositor.start();

        }

        for(Thread receiver : receiverThread){
            receiver.start();
        }

        for (Thread thread: threads) {
            thread.start();
        }

    }
}
