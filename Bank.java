import java.util.HashMap;

public class Bank {

    private HashMap<Integer, Account> accounts = new HashMap<>();

    public void createAccount(int accNo, String name, double initialBalance) {
        if (accounts.containsKey(accNo)) {
            System.out.println("Account already exists!");
        } else {
            accounts.put(accNo, new Account(accNo, name, initialBalance));
            System.out.println("Account created successfully.");
        }
    }

    public Account getAccount(int accNo) {
        return accounts.get(accNo);
    }

    public void deposit(int accNo, double amount) {
        Account acc = getAccount(accNo);
        if (acc != null) {
            acc.deposit(amount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdraw(int accNo, double amount) {
        Account acc = getAccount(accNo);
        if (acc != null) {
            if (acc.withdraw(amount)) {
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void displayAccount(int accNo) {
        Account acc = getAccount(accNo);
        if (acc != null) {
            System.out.println("Account Number: " + acc.getAccountNumber());
            System.out.println("Account Holder: " + acc.getAccountHolderName());
            System.out.println("Balance: " + acc.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }
}
