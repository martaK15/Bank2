package banka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Baza {

    public void setConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://128.140.107.152:3307/bankabaza";
            String user = "root";
            String pass = "malkier";
            try {
                connection = DriverManager.getConnection(url, user, pass);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Konekcija sa bazom podataka je uspesno uspostavljena!");


        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}
