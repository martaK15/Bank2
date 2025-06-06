package banka;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Integer id_user;
    private String name;
    private String surname;
    private String jmbg;
    private String adress;
    private List<Account> accounts = new ArrayList<>();

    public User ( int id_user, String name, String surname, String jmbg, String adress, List<Account> accounts ){
        this.id_user = id_user;
        this.name = name;
        this.surname = surname;
        this.jmbg = jmbg;
        this.adress = adress;
        this.accounts = accounts;
    }

    public Integer getId_user() {
        return id_user;
    }

    public String getName () {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getJmbg() {
        return jmbg;
    }

    public String getAdress() {
        return adress;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String toString() {
        return super.toString();
    }

    public  void payment(double amount, int id_account1, int id_account2, List<Account> accounts) {
        for (Account a : accounts) {
            if (a.getId_account() == id_account1 && a.getCurrent_balance()>=amount) {
                a.setCurrent_balance(a.getCurrent_balance()-amount);
            }
            else
                System.out.println("nemate dovoljno sredstava na racunu");
            for (Account b : accounts) {
                if (b.getId_account() == id_account2) {
                    b.setCurrent_balance(b.getCurrent_balance()+amount);
                }


            }

        }
    }


    public  void payout(double amount, int id_user, int id_account) {
        for (Account a : this.accounts) {
            if (a.getId_account() == id_account && a.getId_user() == id_user) {
                if(a.getCurrent_balance()>=amount)
                a.setCurrent_balance(a.getCurrent_balance()-amount);
                else
                    System.out.println("nemate dovoljno sredstava na racunu");
            }
        }
    }

    public  double check_balace( Account account){
        return account.getCurrent_balance();
    }

   public double check_balances()
    {
        double counter=0;
        for (Account a: this.accounts)
            counter+=a.getCurrent_balance();
        return counter;
    }

}
