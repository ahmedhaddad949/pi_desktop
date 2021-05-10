package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static DataSource instance;
    public Connection cnx;

    private final String URL = "jdbc:mysql://localhost:3307/projet?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PWD = "";

    private DataSource(){
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PWD);
            System.out.println("Connected to DB");
        }catch (SQLException ex ) {
            System.out.println(ex.getMessage());
        }
    }
    public static DataSource getInstance() {
        if (instance == null)
        {   instance = new DataSource(); }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }

}
