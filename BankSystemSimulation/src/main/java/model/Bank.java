package model;

import accounts.SavingsBankAccount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<BankAccount> bankAccounts = new ArrayList<>();


    public void addBankAccount(BankAccount bankAccount){

        for(BankAccount account: bankAccounts){
            if(bankAccount.getBankAccountNumber().equals(account.getBankAccountNumber())){
                System.err.println(account.bankAccountNumber+" Cannot create an account as the account Number Already exists");
                throw new IllegalArgumentException(account.bankAccountNumber+" Cannot create an account as the account Number Already exists ");
            }
        }

        bankAccounts.add(bankAccount);
    }

    public boolean removeAccount(String accountNumber){
        for(BankAccount account: bankAccounts){
            if(account.getBankAccountNumber().equals(accountNumber)){
                this.bankAccounts.remove(account);
                System.out.println(account.bankAccountNumber+" Account is removed");
                return true;
            }
        }
        return false;
    }

    public BankAccount getAccount(String accountNumber){
        for(BankAccount account: bankAccounts){
            if(account.getBankAccountNumber().equals(accountNumber)){
                return account;
            }
        }
        return null;
    }

    public synchronized void addMonthlyInterest(){
        for(BankAccount account : bankAccounts){
            if(account.getBalance().compareTo(BigDecimal.ZERO) > 0){
                if(account instanceof SavingsBankAccount){
                    SavingsBankAccount savingsBankAccount = ((SavingsBankAccount)account);
                    BigDecimal interest = calculateMonthlyInterest(savingsBankAccount);
                    savingsBankAccount.addMonthlyInterest(interest);
                }

            }
        }
    }

    private BigDecimal calculateMonthlyInterest(SavingsBankAccount account){
        return account.getBalance()
                .multiply(account.getInterestRate())
                .divide(BigDecimal.valueOf(100), 12, RoundingMode.HALF_UP) // Set scale to 12 decimal places
                .divide(BigDecimal.valueOf(12), 12, RoundingMode.HALF_UP); // Set scale to 12 decimal places

    }

    public synchronized void deductIncomeTax(){
        for(BankAccount account : bankAccounts){
            if(account.getBalance().compareTo(BigDecimal.ZERO) > 0){
                if(account instanceof SavingsBankAccount) {
                    SavingsBankAccount savingsBankAccount = ((SavingsBankAccount)account);
                    BigDecimal interest = calculateMonthlyInterest(savingsBankAccount);
                    BigDecimal tax = interest.multiply(savingsBankAccount.getTaxRate());
                    savingsBankAccount.deductIncomeTax(tax);
                }
            }
        }
    }

    public synchronized void deductAnnualCharges(){
        for(BankAccount account : bankAccounts){
            if(account.getBalance().compareTo(BigDecimal.ZERO) > 0){
                if(account instanceof SavingsBankAccount){
                    SavingsBankAccount savingsBankAccount = ((SavingsBankAccount)account);
                    savingsBankAccount.deductAnnualCharge(savingsBankAccount.getAnnualChargeAmount());
                }

            }
        }
    }

//    public void overDraftCharges(){
//        for(BankAccount account : bankAccounts){
//            if(account.checkBalance().compareTo(BigDecimal.ZERO) > 0){
//                //BigDecimal interest = account.checkBalance().multiply(account.getInterestRate()).divide(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(12));
//                //BigDecimal tax = interest.multiply(BigDecimal.valueOf(.36));
//                //account.deductIncomeTax(tax);
//            }
//        }
//    }
}
