package banka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();

        User u1 = new User(1, "Milan", "Jovanović", "1234567890123", "Beograd, Kralja Petra 10");
        User u2 = new User(2, "Ana", "Marković", "9876543210987", "Novi Sad, Bulevar Oslobođenja 45");

        Map<String, Double> excangeRate = Map.of("RSD->EUR",0.17,"EUR->RSD",117.0);
        Bank banka = new Bank(1, "RajhFajzen", "Milutova 3", excangeRate
        );
        List<Account> acc = new ArrayList<>();
        acc.add(new Account(1, Type.RSD, 12000.0, "RS35100000000000000001", 1, 201, banka));
        acc.add(new Account(2, Type.EUR, 850.5, "RS35100000000000000002", 1, 201, banka));
        acc.add(new Account(3, Type.USD, 430.0, "RS35100000000000000003", 2, 202, banka));
        acc.add(new Account(4, Type.RSD, 9800.0, "RS35100000000000000004", 2, 203, banka));


        u1.addAccount(acc.get(0));
        u1.addAccount(acc.get(1));
        u2.addAccount(acc.get(2));
        u2.addAccount(acc.get(3));
        //u1.addAccount(new Account(2, Type.EUR, 1500.0, "RS35100000000000000002", 1, 1));
        //u2.addAccount(new Account(3, Type.USD, 750.5, "RS35100000000000000003", 2, 1));

        users.add(u1);
        users.add(u2);

        Map<String, Double> exchange_rate_list = new HashMap<>();

        System.out.println(u1.checkBalance(u1.getAccounts().get(0)));
        System.out.println(u2.checkBalance(u2.getAccounts().get(0)));
        System.out.println(u1.checkBalance(u1.getAccounts().get(1)));
        System.out.println(u2.checkBalance(u2.getAccounts().get(1)));



        /*
        System.out.println(u1.checkBalance(u1.getAccounts().get(0)));
        u1.payout(20,101,1,Type.EUR,cLists);
        u1.payment(20,101,1,Type.EUR,cLists);
        System.out.println(u1.checkBalance(u1.getAccounts().get(0)));
*/
        System.out.println("Martino cedo:");
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
