package banka;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private Integer id_bank;
    private String name;
    private String address;
    private Map<String, Double> exchange_rate_list=new HashMap<>();
    private List<Account> accounts = new ArrayList<>();


    // konstruktor
    public Bank ( int id_bank, String name, String address, Map<String, Double> exchange_rate_list ){
        this.id_bank = id_bank;
        this.name = name;
        this.address = address;
        this.exchange_rate_list = exchange_rate_list;
    }

    public Bank(int id_bank, String name) {
        this.id_bank = id_bank;
        this.name = name;
    }

    public Bank(int idBank, String name, String address) {
        this.id_bank = idBank;
        this.name = name;
        this.address = address;
    }

    // geteri
    public Integer getId_bank() {
        return id_bank;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Map<String, Double> getExchange_rate_list () {
        return exchange_rate_list;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    // seteri
    public void setId_bank(Integer id_bank) {
        this.id_bank = id_bank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setExchange_rate_list(Map<String, Double> exchange_rate_list) {
        this.exchange_rate_list = exchange_rate_list;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    // konvertovanje
    public double getExchangeRate(Type from, Type to) {
        if (from == to) return 1.0;
        String key = from.name() + "->" + to.name();
        Double rate = exchange_rate_list.get(key);
        if (rate == null) {
            throw new IllegalArgumentException("Exchange rate not available for: " + key);
        }
        return rate;
    }

    public double convert(double amount, Type fromType, Type toType, Bank bank) {
        double rate = bank.getExchangeRate(fromType, toType);
        return amount * rate;
    }

    // dodavanje novog racuna
    public void addAccount(Account account) {
        if (account == null) return;
        for (Account a : accounts) {
            if (a.getId_account().equals(account.getId_account())) {
                System.out.println("An account with that ID already exist.");
                return;
            }
        }
        accounts.add(account);
        System.out.println("Account successfully added.");
    }

    public void changeExchangeRateListBaza(Baza baza,  String key_, int id_bank, double newRate) throws SQLException, ClassNotFoundException {

        Connection c = null;
        try {
            c = baza.setConnection();
            String query = "UPDATE exchange_rate_list SET value_=? WHERE id_bank=? AND key_=?";
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst())
                System.out.println("There is no rate like that");
            ps.setDouble(1, newRate);
            ps.setInt(2,id_bank);
            ps.setString(3,key_);
            ps.executeQuery();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




        }

    public void changeExchangeRateList(String fromTo,double rate) {

        Baza baza=new Baza();
        exchange_rate_list.put(fromTo,rate);
        try {
            changeExchangeRateListBaza(baza,fromTo,id_bank,rate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public String toString() {
        return "Bank{" +
                "id_bank=" + id_bank +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", exchange_rate_list=" + exchange_rate_list +
                ", accounts=" + accounts +
                '}';
    }
}