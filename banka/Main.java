package banka;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void instertIntoBank(String name, String address) throws SQLException, ClassNotFoundException {

        Baza baza=new Baza();
        try {
            Connection c =baza.setConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String query="insert into bank (name,address) values (?,?)";
        PreparedStatement ps= null;
        try {
            ps = baza.runQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ps.setString(1,name);
        ps.setString(2,address);
        ps.executeUpdate();
    }

    public static void deleteFromBank(String nameTable, String nameBank) throws SQLException, ClassNotFoundException {

        Baza baza=new Baza();
        try {
            Connection c =baza.setConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String query="DELETE FROM bank WHERE name=?";
        PreparedStatement ps= null;
        try {
            ps = baza.runQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //ps.setString(1,nameTable);
        ps.setString(1,nameBank);
        ps.executeUpdate();
    }


    public static void deleteFromExchangeRateList( String key, int id_bank) throws SQLException, ClassNotFoundException {

        Baza baza=new Baza();
        try {
            Connection c =baza.setConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String query="DELETE FROM ? WHERE key_=? AND id_bank=?";
        PreparedStatement ps= null;
        try {
            ps = baza.runQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ps.setString(1,key);
        ps.setInt(2,id_bank);
        ps.executeUpdate();
    }

    public static void insertIntoExchangeRateList( String key, int id_bank, double rate) throws SQLException, ClassNotFoundException {

        Baza baza=new Baza();
        try {
            Connection c =baza.setConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String query=" insert into exchange_rate_list (key_,value_,id_bank) values (?,?,?)";
        PreparedStatement ps= null;
        try {
            ps = baza.runQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ps.setString(1,key);
        ps.setDouble(2,rate);
        ps.setInt(3,id_bank);
        ps.executeUpdate();
    }

    public static void insertIntoUser(String name, String surname, String jmbg, String address) throws SQLException, ClassNotFoundException {

        Baza baza=new Baza();
        try {
            Connection c =baza.setConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String query="insert into user ( name, surname, jmbg, address ) values ( ?, ?, ?, ?)";
        PreparedStatement ps= null;
        try {
            ps = baza.runQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ps.setString(1,name);
        ps.setString(2,surname);
        ps.setString(3,jmbg);
        ps.setString(4,address);
        ps.executeUpdate();
    }

    public static void fillUsers(List<User> users) throws SQLException {
        Baza baza = new Baza();

        try {
            Connection c = baza.setConnection();
            String query = "SELECT * FROM user";
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst())
                System.out.println("There is no user");
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String jmbg = rs.getString("jmbg");
                String address = rs.getString("address");
                int id_user = rs.getInt("id_user");
                User u=new User(id_user,name,surname,address,jmbg);
                users.add(u);

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void fillBanks(List<Bank> banks) throws SQLException {
        Baza baza = new Baza();

        try {
            Connection c = baza.setConnection();
            String query = "SELECT * FROM bank";
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst())
                System.out.println("There are no banks");
            while (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                int id_bank = rs.getInt("id_bank");
                Bank b=new Bank(id_bank,name,address);
                banks.add(b);

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void fillAccounts(List<Account> accounts) throws SQLException {
        Baza baza = new Baza();

        try {
            Connection c = baza.setConnection();
            String query = "SELECT * FROM account";
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst())
                System.out.println("There are no accounts");
            while (rs.next()) {
                String number = rs.getString("number");
                String typeStr = rs.getString("type");
                Type type = Type.valueOf(typeStr);
                int id_bank = rs.getInt("id_bank");
                int id_account = rs.getInt("id_account");
                int id_user = rs.getInt("id_user");
                double current_balance = rs.getDouble("current_balance");
                Account a=new Account(id_account,type,current_balance,number,id_user,id_bank);
                accounts.add(a);

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void fillExchangeRateList( List<Bank> banks) throws SQLException {
        Baza baza = new Baza();
        try {
            Connection c = baza.setConnection();
            String query = "SELECT * FROM exchange_rate_list";
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst())
                System.out.println("There are no exchange rate lists");
            while (rs.next()) {
                String key_ = rs.getString("key_");
                Double value_ = rs.getDouble("value_");
                int id_bank = rs.getInt("id_bank");
                for( Bank b : banks){
                    if(b.getId_bank()==id_bank){
                        b.getExchange_rate_list().put(key_,value_);
                    }
                }


            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void insertIntoAccount(Double current_balance,
                                         String number,
                                         Integer id_user,
                                         Integer id_bank,
                                         Type type,
                                         String jmbg) throws SQLException, ClassNotFoundException {

        Baza baza=new Baza();
        try {
            Connection c =baza.setConnection();
        } catch (ClassNotFoundException e) {
            System.err.println("Error while connecting to database");
            return;
        }
        String query="insert into account (current_balance, number, id_user, id_bank, type,jmbg ) values ( ?, ?, ?, ?, ?,?)";
        PreparedStatement ps= null;
        try {
            ps = baza.runQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ps.setDouble(1,current_balance);
        ps.setString(2,number);
        ps.setInt(3,id_user);
        ps.setInt(4,id_bank);
        ps.setString(5,type.name());
        ps.setString(6,jmbg);
        ps.executeUpdate();
    }

    public static void deleteFromUser(String jmbg) throws SQLException, ClassNotFoundException {

        Baza baza=new Baza();
        try {
            Connection c =baza.setConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String query="DELETE FROM bank WHERE jmbg=?";
        PreparedStatement ps= null;
        try {
            ps = baza.runQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //ps.setString(1,nameTable);
        ps.setString(1,jmbg);
        ps.executeUpdate();
    }

    public static void deleteFromAccount(String number) throws SQLException, ClassNotFoundException {

        Baza baza=new Baza();
        try {
            Connection c =baza.setConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String query="DELETE FROM bank WHERE jmbg=?";
        PreparedStatement ps= null;
        try {
            ps = baza.runQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //ps.setString(1,nameTable);
        ps.setString(1,number);
        ps.executeUpdate();
    }

    //napraviti delete from account


    public static void main(String[] args) throws SQLException, ClassNotFoundException {


       // instertIntoBank("NLB","Bulevar Milutina Milankovica");
       // deleteFromBank("bank","Aik");
       // insertIntoExchangeRateList("RSD->USD",4,1.30);
       // instertIntoBank("Aik","Bulevar Despota Stefana");

       insertIntoUser("Marta", "Kiso", "150700371000", "Bulevar Arsenija Carnojevica");

        //instertIntoBank("NLB","Bulevar Milutina Milankovica");
        //deleteFromBank("bank","Aik");
        //insertIntoExchangeRateList("RSD->USD",3,1.30);
        //instertIntoBank("Aik","Bulevar Despota Stefana");

        List<User> users = new ArrayList<>();
        fillUsers(users);
            for(User u:users)
                System.out.println(u);
            List<Bank> banks = new ArrayList<>();
            fillBanks(banks);
            for(Bank b:banks){
                System.out.println(b);
            }
            List<Account> accounts = new ArrayList<>();
            fillAccounts(accounts);
            for(Account a:accounts){
                System.out.println(a);
            }
           fillExchangeRateList(banks);
            for(Bank b:banks){
                System.out.println(b.getExchange_rate_list());
            }
        System.out.println("JMBG " + users.get(0).getJmbg());
        insertIntoAccount(.0,"RS30389428648396",users.get(0).getId_user(),3,Type.EUR,users.get(0).getJmbg());
        banks.get(0).changeExchangeRateList("RSD->EUR",111.0);

        //Baza baza=new Baza();
        //Connection c =baza.setConnection();

        /*if (!rs.isBeforeFirst()) {
            System.out.println( "Nema prevoda za izabrani jezik!");
        }

        while (rs.next()) {
            String ime= rs.getString("name");
            System.out.println( ime);

        }
/*
        List<User> users = new ArrayList<>();

        User user1 = new User(1, "Milan", "Jovanović", "1234567890123", "Beograd, Kralja Petra 10");
        User user2 = new User(2, "Ana", "Marković", "9876543210987", "Novi Sad, Bulevar Oslobođenja 45");
        User user3 = new User(3, "Nikola", "Popovic", "1234566880123", "Nis, Kralja Petra 2");
        User user4 = new User(4, "Lazar", "Simic", "9876543210000", "Novi Sad, Bulevar Oslobođenja 4");
        User user5 = new User(5, "Nevena", "Stankovic", "1234567890111", "Beograd, Kralja Petra 13");
        User user6 = new User(6, "Petar", "Marinkovic", "9876543330987", "Subotica, Bulevar Oslobođenja 41");

        Map<String, Double> exchangeRate1 = new HashMap<>(Map.of("RSD->EUR",0.16,"EUR->RSD",116.0,"USD->RSD",110.0));
        Bank bank1 = new Bank(1, "Banca Intesa", "Glavna 30", exchangeRate1);

        Map<String, Double> exchangeRate2 = new HashMap<>(Map.of("RSD->EUR",0.17,"EUR->RSD",117.0));
        Bank bank2 = new Bank(2, "OTP Banka Srbija", "Bulevar Mihaila Pupina 165", exchangeRate2);

        Map<String, Double> exchangeRate3 =new HashMap<>( Map.of("RSD->EUR",0.18,"EUR->RSD",118.0));
        Bank bank3 = new Bank(3, "Raiffeisen banka", "Đorđa Stanojevića 16", exchangeRate3);

        Map<String, Double> exchangeRate4 = new HashMap<>(Map.of("RSD->EUR",0.19,"EUR->RSD",119.0));
        Bank bank4 = new Bank(4, "UniCredit Bank Srbija", "Ivana Milutinovića 48", exchangeRate4);

        List<Account> acc = new ArrayList<>();
        acc.add(new Account(1, Type.RSD , 850.5, "RS35100000000000000001", 1, 201, bank1));
        acc.add(new Account(2, Type.EUR, 850.5, "RS35100000000000000002", 1, 202, bank2));
        acc.add(new Account(3, Type.USD, 430.0, "RS35100000000000000003", 2, 203, bank3));
        acc.add(new Account(4,Type.RSD, 9800.0, "RS35100000000000000004", 2, 204, bank4));
        acc.add(new Account(5,Type.RSD, 12000.0, "RS35100000000000000005", 3, 205, bank1));
        acc.add(new Account(6, Type.EUR, 850.5, "RS35100000000000000006", 4, 206, bank2));
        acc.add(new Account(7, Type.USD, 430.0, "RS35100000000000000007", 5, 207, bank3));
        acc.add(new Account(8,Type.RSD, 9800.0, "RS35100000000000000008", 6, 208, bank4));

        user1.addAccount(acc.get(0));
        user1.addAccount(acc.get(1));
        user2.addAccount(acc.get(0));
        user2.addAccount(acc.get(1));
        user3.addAccount(acc.get(0));
        user4.addAccount(acc.get(0));
        user5.addAccount(acc.get(0));
        user6.addAccount(acc.get(0));

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);

        System.out.println(user1.checkBalance(user1.getAccounts().get(0)));
        System.out.println(user2.checkBalance(user2.getAccounts().get(0)));
        System.out.println(user1.checkBalance(user1.getAccounts().get(1)));
        System.out.println(user2.checkBalance(user2.getAccounts().get(1)));

        var acc1 = acc.get(0);
        var acc2 = acc.get(1);
        try {
            acc1.payment(acc2, 10000);
        } catch (NoEnoughFundsException | NegativeAmountException e) {
            System.err.println("You don't have enough funds in your account");
        }
        System.out.println(acc1);
        try {
            acc1.payout(Type.RSD, 500.00);
        } catch (NoEnoughFundsException | NegativeAmountException e) {
            System.out.println("Greška u plaćanju: " + e.getMessage());
        }

        System.out.println(acc1);
        System.out.println(acc2);
        bank1.changeExchangeRateList("RSD->EUR",0.0085);
        bank1.getExchange_rate_list().put("USD->RSD", 100.5);
        System.out.println(bank1.getExchange_rate_list());



        Baza baza=new Baza();
        Connection c =baza.setConnection();
        String query="SELECT name FROM user WHERE name=?";
        PreparedStatement ps= baza.runQuery(query);
        ps.setString(1,"Ana");
        ResultSet rs=ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            System.out.println( "Nema prevoda za izabrani jezik!");
        }

        while (rs.next()) {
            String ime= rs.getString("name");
            System.out.println( ime);

        }



*/
    }

}
