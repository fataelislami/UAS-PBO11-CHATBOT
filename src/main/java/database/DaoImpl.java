package database;

import Model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DaoImpl implements Dao {

    private final static String USER_TABLE="tbl_user";
    private final static String SQL_SELECT_ALL="SELECT * FROM tbl_user";
    private final static String SQL_GET_BY_USER_ID=SQL_SELECT_ALL + " WHERE LOWER(user_id)=(?);";
    private final static String SQL_REGISTER="INSERT INTO "+USER_TABLE+" (user_id, flag , display_name,lat,lng) VALUES (?,?,?,?,?);";
    private final static String UPDATE="UPDATE tbl_user set flag=? WHERE user_id=?;";

    @Override
    public List<String> getByUserId(String aUserId) {
        Config oConfig=new Config();
        List<String> obj=new ArrayList<>();
        try (Connection conn = oConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_GET_BY_USER_ID)) {

            pstmt.setString(1, aUserId);
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

    @Override
    public int RegisterUser(User user) {

        int id = 0;
        Config oConfig=new Config();
        try (Connection conn = oConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_REGISTER,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getUser_id());
            pstmt.setString(2, user.getFlag());
            pstmt.setString(3, user.getDisplay_name());
            pstmt.setString(4, user.getLat());
            pstmt.setString(5, user.getLng());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                id=1;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    @Override
    public int UpdateFlag(String UserId, String flag) {

        int affectedrows = 0;
        Config oConfig=new Config();
        try (Connection conn = oConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE)) {

            pstmt.setString(1, flag);
            pstmt.setString(2, UserId);

            affectedrows = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return affectedrows;
    }



}
