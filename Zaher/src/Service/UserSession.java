package Service;

public class UserSession {
    public static UserSession getInstance() {
        return instance;
    }

    public static UserSession instance;
    private static String userName;
    private static String name;
    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public UserSession(String name, String userName) {
        this.userName = userName;
        this.name = name;
    }



    public static void setInstance(UserSession instance) {
        UserSession.instance = instance;
    }



    public static String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public static UserSession getInstance(String userName, String name) {
        if (instance == null ){
            instance = new UserSession(name,userName);
        }
        return instance;

    }
    public static void cleanUserSession(){
        userName = "";
        name ="";
        instance = null;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
