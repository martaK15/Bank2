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

    public void paytoaccount(Account b, double amount, int id_account2) {
       // Account b=FindAccount(accounts, id_account2);
        b.setCurrent_balance(b.getCurrent_balance() + amount);
    }



    public Account FindAccount (List<Account> accounts, int id_account) {
        for (Account a : accounts) {
            if (a.getId_account() == id_account)
                return a;
        }
        System.out.println("ERROR: Account doesn't exist");
        return null;
    }

    public CList FindCurr (List<CList> clist, Type typeocur) {

        for (CList c : clist) {
            if (c.getType() == typeocur)
                return c;
        }
        System.out.println("ERROR: value doesn't exist");
        return null;

    }

    public double convertSell(double amount, List<CList> clist, Type typeOfCurr) {

        CList c = FindCurr(clist, typeOfCurr);
        amount = amount * c.getSell();
        return amount;
    }

    public double convertBuy(double amount, List<CList> clist, Type typeOfCurr)
    {

        CList c = FindCurr(clist, typeOfCurr);
        amount = amount / c.getBuy();
        return amount;
    }

    public  void payment(double amount, int id_account1, int id_account2, List<Account> accounts, Type typeOfCurr, List<CList> clist) {
        Account a = FindAccount(accounts, id_account1);
        if( a.getType() == typeOfCurr) {
            if (a.getCurrent_balance() >= amount) {
                a.setCurrent_balance(a.getCurrent_balance() - amount);
            }
            else
                System.out.println("nemate dovoljno sredstava na racunu");
        }
        else
        {
            amount =convertSell(amount,clist, typeOfCurr);
            if (a.getCurrent_balance() >= amount) {
                a.setCurrent_balance(a.getCurrent_balance() - amount);
                paytoaccount(a, convertBuy(amount, clist, typeOfCurr), id_account2);
            }
            else
                System.out.println("nemate dovoljno sredstava na racunu");
        }

    }


    public  void payout(double amount, int id_user, int id_account, Type type, List<CList> clist) {
         {
             Account a=FindAccount(accounts, id_account);
            if ( a.getType().equals(type)) {
                if(a.getCurrent_balance()>=amount)
                    a.setCurrent_balance(a.getCurrent_balance() - amount);
                else
                    System.out.println("You don't have enough balance");
            }
            else
            {
                CList c=FindCurr(clist, type);
                amount = convertSell(amount,clist,type);
                if (a.getCurrent_balance()>=amount) {
                    a.setCurrent_balance(a.getCurrent_balance()-amount);
                }
            }
        }
    }

    public  double checkBalance(Account account){
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
