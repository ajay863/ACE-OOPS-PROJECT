import java.util.Scanner;

// Abstract Account class
abstract class Account {
    private String accountNumber;
    private String holderName;
    protected double balance;

    public Account(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }
}

// SavingsAccount class
class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountNumber, String holderName, double balance, double interestRate) {
        super(accountNumber, holderName, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void calculateInterest() {
        double interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("Interest added: " + interest);
    }
}

// CurrentAccount class
class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, String holderName, double balance, double overdraftLimit) {
        super(accountNumber, holderName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (balance + overdraftLimit) >= amount) {
            balance -= amount;
        } else {
            System.out.println("Overdraft limit exceeded or invalid amount.");
        }
    }
}

// Main class to test the implementation
public class BankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SavingsAccount savingsAccount = null;
        CurrentAccount currentAccount = null;

        while (true) {
            System.out.println("\nBanking System Menu:");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Current Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Calculate Interest (Savings Account)");
            System.out.println("6. Show Balance");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String saAccountNumber = scanner.nextLine();
                    System.out.print("Enter holder name: ");
                    String saHolderName = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double saBalance = scanner.nextDouble();
                    System.out.print("Enter interest rate: ");
                    double interestRate = scanner.nextDouble();
                    savingsAccount = new SavingsAccount(saAccountNumber, saHolderName, saBalance, interestRate);
                    System.out.println("Savings account created successfully!");
                    break;

                case 2:
                    System.out.print("Enter account number: ");
                    String caAccountNumber = scanner.nextLine();
                    System.out.print("Enter holder name: ");
                    String caHolderName = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double caBalance = scanner.nextDouble();
                    System.out.print("Enter overdraft limit: ");
                    double overdraftLimit = scanner.nextDouble();
                    currentAccount = new CurrentAccount(caAccountNumber, caHolderName, caBalance, overdraftLimit);
                    System.out.println("Current account created successfully!");
                    break;

                case 3:
                    System.out.print("Enter account number: ");
                    String depositAccountNumber = scanner.nextLine();
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    if (savingsAccount != null && savingsAccount.getAccountNumber().equals(depositAccountNumber)) {
                        savingsAccount.deposit(depositAmount);
                        System.out.println("Amount deposited to savings account.");
                    } else if (currentAccount != null && currentAccount.getAccountNumber().equals(depositAccountNumber)) {
                        currentAccount.deposit(depositAmount);
                        System.out.println("Amount deposited to current account.");
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter account number: ");
                    String withdrawAccountNumber = scanner.nextLine();
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (savingsAccount != null && savingsAccount.getAccountNumber().equals(withdrawAccountNumber)) {
                        savingsAccount.withdraw(withdrawAmount);
                        System.out.println("Amount withdrawn from savings account.");
                    } else if (currentAccount != null && currentAccount.getAccountNumber().equals(withdrawAccountNumber)) {
                        currentAccount.withdraw(withdrawAmount);
                        System.out.println("Amount withdrawn from current account.");
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 5:
                    if (savingsAccount != null) {
                        savingsAccount.calculateInterest();
                    } else {
                        System.out.println("No savings account found.");
                    }
                    break;

                case 6:
                    System.out.print("Enter account number: ");
                    String balanceAccountNumber = scanner.nextLine();
                    if (savingsAccount != null && savingsAccount.getAccountNumber().equals(balanceAccountNumber)) {
                        System.out.println("Savings Account Balance: " + savingsAccount.getBalance());
                    } else if (currentAccount != null && currentAccount.getAccountNumber().equals(balanceAccountNumber)) {
                        System.out.println("Current Account Balance: " + currentAccount.getBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}