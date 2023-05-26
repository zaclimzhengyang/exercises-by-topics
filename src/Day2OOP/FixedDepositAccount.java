package Day2OOP;

import java.util.ArrayList;
import java.util.Date;

public class FixedDepositAccount extends BankAccount {
    private float interest;
    private int durationInMonths;
    private boolean interestChanged;
    private boolean durationChanged;


    public FixedDepositAccount(String accountHolderName, float balance) {
        super(accountHolderName, balance);
        this.interest = 3;
        this.durationInMonths = 6;
        this.interestChanged = false;
        this.durationChanged = false;
    }

    public FixedDepositAccount(String accountHolderName, float balance, float interest) {
        super(accountHolderName, balance);
        setInterest(interest);
    }


    public FixedDepositAccount(String accountHolderName, float balance, float interest, int duration) {
        this(accountHolderName, balance, interest);
        setDurationInMonths(duration);
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        if (interestChanged) {
            throw new IllegalArgumentException("Interest can only be changed once");
        }
        this.interest = interest;
        interestChanged = true;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(int duration) {
        if (durationChanged) {
            throw new IllegalArgumentException("Duration can be changed only once");
        }
        durationInMonths = duration;
        durationChanged = true;
    }

    @Override
    public void deposit(float amount) {
        // NOP - No Operations
    }

    @Override
    public void withdraw(float amount) {
        // NOP - No Operations
    }

    @Override
    public float getAccountBalance() {
        return (interest * super.getAccountBalance() / 100) + super.getAccountBalance();
    }

    public static void main(String[] args) {
        FixedDepositAccount fd = new FixedDepositAccount("asd",300);
        float baBalance = fd.getAccountBalance();
        System.out.println(baBalance);
    }
}
