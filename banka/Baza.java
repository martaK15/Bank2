package banka;

import java.sql.*;

public class Baza {

    public Connection setConnection() throws ClassNotFoundException, SQLException {
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
        return connection;
    }

    public PreparedStatement runQuery(String query) throws SQLException, ClassNotFoundException {
        try {
            Connection c= setConnection();
            PreparedStatement ps=c.prepareStatement(query);
            return ps;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
