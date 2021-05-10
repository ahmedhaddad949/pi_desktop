package Service;

public class UserSession {
    public static UserSession getInstance() {
        return instance;
    }
    private static int id;
    public static UserSession instance;
    private static String userName;

    public static void setRole(String role) {
        UserSession.role = role;
    }

    private static String role;
    public static String getRole() {
        return role;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        UserSession.id = id;
    }





    public UserSession(int id,String name, String userName) {
        this.id = id;
        this.userName = userName;
        this.role = name;
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


    public static UserSession getInstance(int id ,String userName, String role) {
        if (instance == null ){
            instance = new UserSession(id ,role,userName);
        }
        return instance;

    }
    public static void cleanUserSession(){
        id = 0;
        userName = "";
        role ="";
        instance = null;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
