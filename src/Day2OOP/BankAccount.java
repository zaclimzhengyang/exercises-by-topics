package Day2OOP;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class BankAccount {
    private final String accountHolderName;
    private String accountNumber = generateAccountNumber();
    private float accountBalance = 0;
    private ArrayList<String> transactions = new ArrayList<String>();
    private boolean isClosed = false;
    private LocalDateTime accountCreationDate = LocalDateTime.now();
    private LocalDateTime accountClosingDate = null;

    public BankAccount(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public BankAccount(String accountHolderName, float initialBalance) {
        this(accountHolderName);
        this.accountBalance = initialBalance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public float getAccountBalance() {
        return accountBalance;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public LocalDateTime getAccountCreationDate() {
        return accountCreationDate;
    }

    public LocalDateTime getAccountClosingDate() {
        return accountClosingDate;
    }

    public String generateAccountNumber() {
        Random random = new Random();
        return String.valueOf(random.nextInt(10000) + 100000);
    }

    public void closeAccount() {
        isClosed = true;
        accountClosingDate = LocalDateTime.now();
    }

    public void deposit(float amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        if (isClosed) {
            throw new IllegalArgumentException("Account is closed");
        }
        accountBalance += amount;
        StringBuilder sb = new StringBuilder();
        sb.append("Deposit $");
        sb.append(String.valueOf(amount));
        sb.append(" at ");
        sb.append(LocalDateTime.now());
        System.out.println(sb.toString());
        transactions.add(sb.toString());
    }

    public void withdraw(float amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive");
        }
        if (isClosed) {
            throw new IllegalArgumentException("Account is closed");
        }
        if (amount > accountBalance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        accountBalance -= amount;
        String transaction = "Withdraw $" + amount + " at " + LocalDateTime.now();
        System.out.println(transaction);
        transactions.add(transaction);
    }


    public static void main(String[] args) {
        BankAccount ba = new BankAccount("ad",0);
        ba.deposit(100);
        ba.withdraw(50);
    }


}
