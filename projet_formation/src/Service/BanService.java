package Service;

import Entities.Users;
import util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BanService {
    Connection cnx = DataSource.getInstance().getCnx();


    public int name2id(String name) throws SQLException {
        int sixtynine = 0;
        String NAME2IDQUERY = "SELECT id FROM USERS WHERE USERNAME = '" + name + "'";
        ResultSet res = null;

        res = cnx.createStatement().executeQuery(NAME2IDQUERY);
        while (res.next()) {
            sixtynine = res.getInt(1);
        }
        return sixtynine;

    }

    public int checkBan(String name) throws SQLException {
        int id = name2id(name);
        int test = 0;
        String VERIFY_BAN = "select count(*) from BANNED as  B INNER  JOIN USERS as U on B.id_user=" + id;
        try {
            ResultSet c = cnx.createStatement().executeQuery(VERIFY_BAN);
            while (c.next()) {
                test = c.getInt(1);
                System.out.println(test);
                return c.getInt(1);


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();


        }
        System.out.println(test);
        return test;
    }
}
