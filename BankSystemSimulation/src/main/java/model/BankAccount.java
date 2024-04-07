package model;

import util.AccountType;

import java.math.BigDecimal;
import java.util.List;

public abstract class BankAccount {

    protected String bankAccountNumber;


    //for joint accounts
    private List<Customer> customers;

    //accountType
    private AccountType accountType;


    protected volatile BigDecimal balance;


    public BankAccount(String bankAccountNumber,  List<Customer> customers, AccountType accountType) {
        this.bankAccountNumber = bankAccountNumber;
        this.customers = customers;
        this.accountType = accountType;
        this.balance = BigDecimal.ZERO;
    }


    public synchronized String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public synchronized BigDecimal getBalance(){
        return balance;
    }


    public List<Customer> getCustomers() {
        return customers;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setCustomers(Customer customer) {
        this.customers.add(customer);
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }


    public boolean removeCustomer(String customerId){
        for(Customer customer: customers){
            if(customer.getCustomerId().equals(customerId)){
                this.customers.remove(customer);
                return false;
            }
        }
        return true;
    }

    public boolean isOwner(Customer customer) {
        return customers.contains(customer);
    }

    public abstract BigDecimal withdraw(BigDecimal amount);

    public synchronized void deposit(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) > 0){
            this.balance = this.balance.add(amount);
            System.out.println(this.bankAccountNumber +" Deposit successfully : amount  :"+ amount + " | balance : "+ this.balance);

        }
        else
            throw new IllegalArgumentException(this.bankAccountNumber +" Amount you wish to deposit cannot be 0 or below");
    }





}
