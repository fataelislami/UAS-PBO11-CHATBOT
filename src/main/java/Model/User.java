package Model;

public class User
{

    public String user_id;
    public String flag;
    public String display_name;
    public String lat;
    public String lng;

    public User(String aUserId, String aFlag, String aDisplayName)
    {
        user_id=aUserId;
        flag=aFlag;
        display_name=aDisplayName;
    }

    public User()
    {

    }
};