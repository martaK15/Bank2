package banka;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Integer id_user;
    private String name;
    private String surname;
    private String jmbg;
    private String address;
    private List<Account> accounts = new ArrayList<>();

    public User ( int id_user, String name, String surname, String jmbg, String address, List<Account> accounts ){
        this.id_user = id_user;
        this.name = name;
        this.surname = surname;
        this.jmbg = jmbg;
        this.address = address;
        this.accounts = accounts;
    }

    public User(int idUser, String ime, String surname, String number, String s) {
        this.id_user = idUser;
        this.name = ime;
        this.surname = surname;
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

    public String getAddress() {
        return address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    /**
     * opis
     * @param account asdasd
     * @return asdasd
     */
    public double checkBalance(Account account){
        System.out.print("Balance on account: "+ account.getId_account() +" is:");
        return account.getCurrent_balance();
    }

   public double check_balances()
    {
        double counter=0;
        for (Account a: this.accounts)
            counter+=a.getCurrent_balance();
        return counter;
    }

    public void addAccount(Account a) {
        this.accounts.add(a);
    }

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", jmbg='" + jmbg + '\'' +
                ", address='" + address + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
