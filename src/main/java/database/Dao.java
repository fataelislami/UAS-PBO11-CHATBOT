package database;

import Model.User;

import java.util.List;

public interface Dao {
    public List<User> getByUserId(String aUserId);
    public int RegisterUser(String aUserId,String aFlag,String aDisplayName,String lat,String lng);
}
