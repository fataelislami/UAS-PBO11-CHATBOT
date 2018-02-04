package Model;

public class User
{

    public String user_id;
    public String flag;
    public String display_name;
    public String lat;
    public String lng;

    public String getUser_id() {
        return user_id;
    }

    public String getFlag() {
        return flag;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public User(String user_id, String flag, String display_name, String lat, String lng) {
        this.user_id = user_id;
        this.flag = flag;
        this.display_name = display_name;
        this.lat = lat;
        this.lng = lng;
    }

    public User()
    {

    }
};