package zona_fit.conexion;

import java.sql.*;

public class Conexion {

    public static Connection getConnection() {
        Connection con = null;
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/zona_fit_db?user=root&password=andresaviles123");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(ResultSet rs) {

        try {
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeConnection(PreparedStatement smtm){
        try {
            smtm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        var con = getConnection();
        if (con != null) {
            System.out.println("Hello");
        }
    }

}
