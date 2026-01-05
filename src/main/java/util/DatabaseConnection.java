package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static  final String URL="jdbc:mysql://localhost:3306/session";
    private static  final String USER="root";
    private static  final String PASSWORD="Kumar";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL,USER,PASSWORD);}

    public static void main(String[] args) {
        try(Connection con=getConnection()){
            System.out.println("Connected to database");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
