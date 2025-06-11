package banka;

import java.util.List;

public class Account {
    private Integer id_account;
    private Type type;
    private Double current_balance;
    private String number;
    private User user;
    private Bank bank;
    private Integer id_user;
    private Integer id_bank;


    // konstruktor
    public Account(int idAccount, Type type, double currentBalance, String number, int id_user, int id_account, Bank bank) {
        this.id_account = idAccount;
        this.type = type;
        this.current_balance = currentBalance;
        this.number = number;
        this.id_user = id_user;
        this.id_bank = id_account;
        bank.addAccount(this);
        this.bank = bank;
    }


    // provera stanja racuna
    public static double check_balace(Account account) {
        return account.getCurrent_balance();
    }


    // geteri i seteri
    public Integer getId_account() {
        return id_account;
    }

    public void setId_account(Integer id_account) {
        this.id_account = id_account;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Double getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(Double current_balance) {
        this.current_balance = current_balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId_bank() {
        return id_bank;
    }

    public void setId_bank(Integer id_bank) {
        this.id_bank = id_bank;
    }

    // konstruktor
    public Account(Integer id_account, Type type, Double current_balance, String number, User user, Bank bank) {
        this.id_account = id_account;
        this.type = type;
        this.current_balance = current_balance;
        this.number = number;
        this.user = user;
        this.bank = bank;
    }

    // metoda payment
    public void payment(Account toAccount, double amount) throws NoEnoughFundsException, NegativeAmountException{
        // proveravamo da li je dati iznos negativan broj
        if ( amount < 0 ){
            throw new NegativeAmountException("The amount cannot be a negative number");
        }
        double convertedAmount = amount;
        //proveriti da li se valute jednog i drugog racuna poklapaju
        if (this.type != toAccount.type) {
            // ako se valuta ne poklapa, konvertujemo
            convertedAmount = this.bank.convert(amount, toAccount.type, this.type, this.bank);
        }
        // proveravamo da li imamo dovoljno sredstava na racunu
        if (this.getCurrent_balance() < convertedAmount) {
            // ako nemamo, ispisujemo poruku
            throw new NoEnoughFundsException("You don't have enough funds in your account");
        }

        // ako imamo, skidamo pare sa prvog racuna
        this.setCurrent_balance(current_balance - convertedAmount);
        // dodajemo drugom racunu
        toAccount.setCurrent_balance(toAccount.current_balance + amount);
    }

    // metoda payout
    public void payout(Type type, Double amount) throws NoEnoughFundsException, NegativeAmountException {
        // proveravamo da li je dati iznos negativan broj
        if ( amount < 0 ){
            throw new NegativeAmountException("The amount cannot be a negative number");
        }
        // proveriti da li je moj racun u istoj valuti kao i zeljena valuta
        if (this.type != type) {
            // prvo konvertovati odredjeni iznos u zeljeni iznos
            amount = this.bank.convert(amount, this.type, type, this.bank);
        }
        if (this.getCurrent_balance() >= amount) {
            // ako ima, postaviti trenutni balans na oduzetu vrednost
            this.setCurrent_balance(current_balance - amount);
        } else {
            // ako nema, ispisati poruku
            throw new NoEnoughFundsException("You don't have enough funds in your account");
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "id_account=" + id_account +
                ", type=" + type +
                ", current_balance=" + current_balance +
                ", number='" + number + '\'' +
                ", user=" + user +
                ", bank=" + bank.getName() +
                ", id_user=" + id_user +
                ", id_bank=" + id_bank +
                '}';
    }
}