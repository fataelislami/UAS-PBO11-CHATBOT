package Model;

public class User
{

    public String user_id;
    public String flag;
    public String display_name;
    public String lat;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

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