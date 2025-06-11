package banka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        List<User> users = new ArrayList<>();

        User user1 = new User(1, "Milan", "Jovanović", "1234567890123", "Beograd, Kralja Petra 10");
        User user2 = new User(2, "Ana", "Marković", "9876543210987", "Novi Sad, Bulevar Oslobođenja 45");
        User user3 = new User(3, "Nikola", "Popovic", "1234566880123", "Nis, Kralja Petra 2");
        User user4 = new User(4, "Lazar", "Simic", "9876543210000", "Novi Sad, Bulevar Oslobođenja 4");
        User user5 = new User(5, "Nevena", "Stankovic", "1234567890111", "Beograd, Kralja Petra 13");
        User user6 = new User(6, "Petar", "Marinkovic", "9876543330987", "Subotica, Bulevar Oslobođenja 41");


        Map<String, Double> exchangeRate1 = Map.of("RSD->EUR",0.16,"EUR->RSD",116.0);
        Bank bank1 = new Bank(1, "Banca Intesa", "Glavna 30", exchangeRate1);

        Map<String, Double> exchangeRate2 = Map.of("RSD->EUR",0.17,"EUR->RSD",117.0);
        Bank bank2 = new Bank(2, "OTP Banka Srbija", "Bulevar Mihaila Pupina 165", exchangeRate2);

        Map<String, Double> exchangeRate3 = Map.of("RSD->EUR",0.18,"EUR->RSD",118.0);
        Bank bank3 = new Bank(3, "Raiffeisen banka", "Đorđa Stanojevića 16", exchangeRate3);

        Map<String, Double> exchangeRate4 = Map.of("RSD->EUR",0.19,"EUR->RSD",119.0);
        Bank bank4 = new Bank(4, "UniCredit Bank Srbija", "Ivana Milutinovića 48", exchangeRate4);

        List<Account> acc = new ArrayList<>();
        acc.add(new Account(1, Type.RSD, 12000.0, "RS35100000000000000001", 1, 201, bank1));
        acc.add(new Account(2, Type.EUR, 850.5, "RS35100000000000000002", 1, 202, bank2));
        acc.add(new Account(3, Type.USD, 430.0, "RS35100000000000000003", 2, 203, bank3));
        acc.add(new Account(4, Type.RSD, 9800.0, "RS35100000000000000004", 2, 204, bank4));
        acc.add(new Account(5, Type.RSD, 12000.0, "RS35100000000000000005", 3, 205, bank1));
        acc.add(new Account(6, Type.EUR, 850.5, "RS35100000000000000006", 4, 206, bank2));
        acc.add(new Account(7, Type.USD, 430.0, "RS35100000000000000007", 5, 207, bank3));
        acc.add(new Account(8, Type.RSD, 9800.0, "RS35100000000000000008", 6, 208, bank4));

        user1.addAccount(acc.get(0));
        user1.addAccount(acc.get(1));
        user2.addAccount(acc.get(0));
        user2.addAccount(acc.get(1));
        user3.addAccount(acc.get(2));
        user4.addAccount(acc.get(3));
        user5.addAccount(acc.get(4));
        user6.addAccount(acc.get(5));

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
        } catch (NoEnoughFundsException e) {
            System.err.println("You don't have enough funds in your account");
        }
        System.out.println(acc1);
        acc1.payout(Type.RSD, 30000.0);
        System.out.println(acc1);
        System.out.println(acc2);

    }

}
