package LineSDK;

import Model.User;
import database.Dao;
import database.DaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
     List<String> obj=new ArrayList<>();
    public Connection connect() {
         final String url = "jdbc:postgresql://ec2-54-227-244-122.compute-1.amazonaws.com:5432/dcen7h1umbahmi?sslmode=require";
         final String user = "uvhwytdtelcrwd";
         final String password = "768a207bed4674cf88692c1c796cf918cbc8c9ed5ad686748c323ebd2a57a26c";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }


//    private User displayUserId(ResultSet rs) throws SQLException {
//
//        while (rs.next()) {
//            obj.add(rs.getString("user_id"));
//            User obj=new User(rs.getString("user_id"),rs.getString("flag"),rs.getString("display_name"),rs.getString("lat"),rs.getString("lng"));
////            System.out.println(rs.getString("user_id") + "\t"
////                    + rs.getString("flag") + "\t"
////                    + rs.getString("display_name"));
//return obj;
//        }
//    }

    public List<String> findUser(String userId) {
        String SQL = "SELECT user_id,flag,display_name "
                + "FROM tbl_user "
                + "WHERE LOWER(user_id)=?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                obj.add(rs.getString("user_id"));
                obj.add(rs.getString("flag"));
                obj.add(rs.getString("display_name"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return obj;
    }

    public static void main(String[] args) {
        Main main = new Main();
        List<String> obj1=main.findUser("abc123");
        System.out.println(obj1.get(0));
    }
}
