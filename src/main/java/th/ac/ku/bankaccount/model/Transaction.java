package th.ac.ku.bankaccount.model;

public class Transaction {
    private int bankAccountId;
    private TransactionType type;
    private double amount;

    public Transaction(int accountId, TransactionType type, double amount) {
        this.bankAccountId = accountId;
        this.type = type;
        this.amount = amount;
    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "bankAccountId=" + bankAccountId +
                ", type=" + type +
                ", amount=" + amount +
                '}';
    }
}
