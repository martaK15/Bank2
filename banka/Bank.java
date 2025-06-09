package banka;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private Integer id_bank;
    private String name;
    private String adress;
    private List<CList> c_list = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();

    public Bank ( int id_bank, String name, String adress, List<CList> c_list, List<Account> accounts ){
        this.id_bank = id_bank;
        this.name = name;
        this.adress = adress;
        this.c_list = c_list;

        this.accounts = accounts;
    }

    public Integer getId_bank() {
        return id_bank;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public List<CList> getC_list() {
        return c_list;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setId_bank(Integer id_bank) {
        this.id_bank = id_bank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setC_list(List<CList> c_list) {
        this.c_list = c_list;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}