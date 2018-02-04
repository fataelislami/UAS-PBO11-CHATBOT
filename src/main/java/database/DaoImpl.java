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
    private final static String SQL_REGISTER="INSERT INTO "+USER_TABLE+" (user_id, flag , display_name,lat,lng) VALUES (?, ?, ?,?,?);";

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
    public int RegisterUser(String aUserId, String aFlag, String aDisplayName, String lat, String lng) {
        return 0;
    }
}
