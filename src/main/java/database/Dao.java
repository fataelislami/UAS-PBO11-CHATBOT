package database;

import Model.User;

import java.util.List;

public interface Dao {
    public List<String> getByUserId(String aUserId);
    public int RegisterUser(User user);
}
