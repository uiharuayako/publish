package Network;

import java.util.ArrayList;

public class UserInfo {
    String name;
    String ID;
    public UserInfo(String name,String ID){
        this.name = name;
        this.ID = ID;
    }
    public static ArrayList<UserInfo> userList = new ArrayList<>();
}
