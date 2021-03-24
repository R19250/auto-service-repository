package ProjectAuto.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.*;

public class Database {

    private static Connection createConnection() {
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



    public static ObservableList<Order> getDataOrders(){
        Connection conn = createConnection();
        ObservableList<Order> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from sampleGuiTableView");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Order(Integer.parseInt(rs.getString("orderID")),
                        rs.getString("name"), rs.getString("email"),
                        rs.getString("spz"), rs.getString("brand"),
                        rs.getString("model"), rs.getString("date"),
                        rs.getString("time"), rs.getString("note")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }return list;
    }





    public static void insertData(String query) {
        Connection con = createConnection();
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
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
                System.out.println("ID [" + result.getString(1) + "] Name [" + result.getString(2)
                        + "] Email ["+ result.getString(3)+ "] SPZ ["+ result.getString(4)
                        + "] Brand ["+ result.getString(5)+ "] Model ["+ result.getString(6)
                        + "] Date ["+ result.getString(7)+ "] Time ["+ result.getString(8) +"]");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static ResultSet getUsedTimeFromDatabase(String date) {
        Connection con = createConnection();
        try {
            Statement stmt = con.createStatement();
            return stmt.executeQuery(String.format("SELECT time FROM sampleGuiTableView WHERE date='%s'",date));
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}

