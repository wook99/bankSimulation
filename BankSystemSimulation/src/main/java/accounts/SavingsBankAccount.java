package accounts;

import model.BankAccount;
import model.Customer;
import util.AccountType;

import java.math.BigDecimal;
import java.util.List;

public class SavingsBankAccount  extends BankAccount {

    private final BigDecimal interestRate = BigDecimal.valueOf(5);

    private final BigDecimal annualChargeAmount = BigDecimal.valueOf(100);

    private final BigDecimal taxRate = BigDecimal.valueOf(0.36); // 36% tax rate


    public SavingsBankAccount(String accountNumber, List<Customer> customers, AccountType accountType) {
        super(accountNumber,customers, accountType);
    }


    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public BigDecimal getAnnualChargeAmount() {
        return annualChargeAmount;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public synchronized BigDecimal withdraw(BigDecimal amount){
        BigDecimal amountAvailable = balance;
        if(amount.compareTo(BigDecimal.ZERO) > 0){
            if(amount.compareTo(amountAvailable) <= 0){
                this.balance = this.balance.subtract(amount);
                System.out.println(this.bankAccountNumber +" Withdrawal successful : amount  :"+ amount + " | balance : "+ this.balance);
            }
            else {
                System.err.println((this.bankAccountNumber + " Insufficient funds : amount : " + amount + "| balance : " + this.balance));
                throw new IllegalArgumentException(this.bankAccountNumber + "Insufficient funds : amount : " + amount + "| balance : " + this.balance);
            }
        }
        else {
            System.err.println((this.bankAccountNumber + "Amount you wish to withdraw cannot be 0 or below"));
            throw new IllegalArgumentException(this.bankAccountNumber + "Amount you wish to withdraw cannot be 0 or below");
        }
        return amount;
    }

    public synchronized void deductAnnualCharge(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) > 0){
            this.balance = this.balance.subtract(amount);
            System.out.println(this.bankAccountNumber +" Annual charges deducted successfully : amount  :"+ amount + " | balance : "+ this.balance);
        }
        else
            throw new IllegalArgumentException(this.bankAccountNumber + "Amount you wish to deduct annual charges cannot be 0 or below");
    }

    public synchronized void addMonthlyInterest(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) > 0){
            this.balance = this.balance.add(amount);
            System.out.println(this.bankAccountNumber +" Monthly interest is added successfully : amount  :"+ amount + " | balance : "+ this.balance);
        }
        else
            throw new IllegalArgumentException(this.bankAccountNumber + " Amount you wish to add interest cannot be 0 or below");
    }

    //even the balance goes 0 need to excecute this  - only in savings account
    // can add like if the balance is greater than 1000 only generate the interest and from the interets deduct the tax
    public synchronized void deductIncomeTax(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) > 0){
            this.balance = this.balance.subtract(amount);
            System.out.println(this.bankAccountNumber +" Income Tax deducted successfully : amount  :"+ amount + " | balance : "+ this.balance);
        }
        else
            throw new IllegalArgumentException(this.bankAccountNumber +" Amount you wish to deduct tax cannot be 0 or below");
    }


}
