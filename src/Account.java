import java.util.ArrayList;

public class Account {

    private int balance;
    private ArrayList<Transaction> transactions;

    public Account() {
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public void deposit(int amount, String date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant du dépôt doit être positif.");
        }
        balance += amount;
        transactions.add(new Transaction(date, amount, balance));
    }

    public void withdraw(int amount, String date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant du retrait doit être positif.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Fonds insuffisants.");
        }
        balance -= amount;
        transactions.add(new Transaction(date, -amount, balance));
    }

    public void printStatement() {
        System.out.println("DATE       | AMOUNT  | BALANCE");
        for (int i = transactions.size() - 1; i >= 0; i--) {
            System.out.println(transactions.get(i));
        }
    }

    private static class Transaction {
        private String date;
        private int amount;
        private int balance;

        public Transaction(String date, int amount, int balance) {
            this.date = date;
            this.amount = amount;
            this.balance = balance;
        }

        @Override
        public String toString() {
            return String.format("%-10s | %7d | %7d", date, amount, balance);
        }
    }

    public static void main(String[] args) {
        Account account = new Account();
        account.deposit(1000, "10-01-2012");
        account.deposit(2000, "13-01-2012");
        account.withdraw(500, "14-01-2012");
        account.printStatement();
    }
}
