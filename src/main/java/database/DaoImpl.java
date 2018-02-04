package database;

import Model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.activation.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class DaoImpl implements Dao {
    private final static String USER_TABLE="tbl_user";
    private final static String SQL_SELECT_ALL="SELECT user_id,flag, display_name,lat,lng FROM "+USER_TABLE;
    private final static String SQL_GET_BY_USER_ID=SQL_SELECT_ALL + " WHERE LOWER(user_id) LIKE LOWER(?);";
    private final static String SQL_REGISTER="INSERT INTO "+USER_TABLE+" (user_id, flag , display_name) VALUES (?, ?, ?);";

    private JdbcTemplate mJdbc;

    private final static ResultSetExtractor<User> SINGLE_RS_EXTRACTOR=new ResultSetExtractor<User>()
    {
        @Override
        public User extractData(ResultSet aRs)
                throws SQLException, DataAccessException
        {
            while(aRs.next())
            {
                User p=new User(
                        aRs.getString("user_id"),
                        aRs.getString("flag"),
                        aRs.getString("display_name"));
                        aRs.getString("lat");
                        aRs.getString("lng");

                return p;
            }
            return null;
        }
    };
    private final static ResultSetExtractor<List<User>> MULTIPLE_RS_EXTRACTOR=new ResultSetExtractor<List<User>>()
    {
        @Override
        public List<User> extractData(ResultSet aRs)
                throws SQLException, DataAccessException
        {
            List<User> list=new Vector<User>();
            while(aRs.next())
            {
                User p=new User(
                        aRs.getString("user_id"),
                        aRs.getString("flag"),
                        aRs.getString("display_name"));
                        aRs.getString("lat");
                        aRs.getString("lng");
                list.add(p);
            }
            return list;
        }
    };
    public DaoImpl(DataSource aDataSource) {
        mJdbc=new JdbcTemplate((javax.sql.DataSource) aDataSource);
    }

    @Override
    public List<User> getByUserId(String aUserId) {
        return mJdbc.query(SQL_GET_BY_USER_ID,new Object[]{"%"+aUserId+"%"},MULTIPLE_RS_EXTRACTOR);

    }

    @Override
    public int RegisterUser(String aUserId, String aFlag, String aDisplayName,String lat,String lng) {
        return mJdbc.update(SQL_REGISTER, new Object[]{aUserId, aFlag,  aDisplayName,lat,lng});
    }
}
