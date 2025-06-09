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


    public static void payment(double amount, int id_user, int id_account, List<Account> accounts) {
        for (Account a : accounts) {
            if (a.getId_account() == id_account && a.getId_user() == id_user) {
                a.setCurrent_balance(a.getCurrent_balance()+amount);
            }
        }
    }


    public static void payout(double amount, int id_user, int id_account, List<Account> accounts) {
        for (Account a : accounts) {
            if (a.getId_account() == id_account && a.getId_user() == id_user) {
                a.setCurrent_balance(a.getCurrent_balance()-amount);
            }
        }
    }

    public static double check_balace( Account account){
        return account.getCurrent_balance();
    }


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

    public Account(Integer id_account, Type type, Double current_balance, String number, User user, Bank bank) {
        this.id_account = id_account;
        this.type = type;
        this.current_balance = current_balance;
        this.number = number;
        this.user = user;
        this.bank = bank;
    }
}