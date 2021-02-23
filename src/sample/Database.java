package sample;

import java.sql.Connection;
import java.sql.*;

public class Database {

    private Connection createConnection() {
        String url = "jdbc:mariadb://soveticka.eu:3307/swi_work";
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(
                    url,
                    "swi_user",
                    "heslonenilopata"
            );
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void insertData(String name) {
        Connection con = createConnection();
        try {
            Statement stmt = con.createStatement();
            String query = String.format("INSERT INTO testTableYolo (name) VALUES('%s')", name);
            stmt.executeUpdate(query);
            con.close();
            System.out.printf("User %s was successfully added to the Database%n", name);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ResultSet selectData(String query){
        Connection con = createConnection();
        try{
            Statement stmt = con.createStatement();
            return stmt.executeQuery(query);
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }

    public void selectDataArg(String query) {
        Connection con = createConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);
            while(result.next()){
                System.out.println("[" + result.getString(1) + "] [" + result.getString(2)+ "] ");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

