package Service;

import Entities.Users;
import util.DataSource;

import java.sql.*;

public class resetPwdService {
    static int min = 500000;
    static int max = 1000000;

    private static final  int  random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
    public boolean resetPwd(String username) {
        Users c = new Users();
        String result ;
        try {

            String UPDATE_QUERY = "update USERS set password = ?  where username =? ";
            String SELECT_QUERY = "SELECT *  FROM  USERS where username = '"+username+"' ";
            Statement st;
            Connection cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();

            ResultSet res = st.executeQuery(SELECT_QUERY);
            while (res.next()) {
             PreparedStatement ps = cnx.prepareStatement(UPDATE_QUERY);
             ps.setString(1, String.valueOf(random_int));
             ps.setString(2,username);
             ps.execute();
                c.setEmail(res.getString("email"));
                result= c.getEmail();
                System.out.println(result);
                String from = "zahertestamri@gmail.com";
                String pass = "swdzaher2050";
                String[] to = { ""+result }; // list of recipient email addresses
                String subject = "Account Password Reset NO REPLY";
                String body = "Your account reset password code is :   "+random_int+ " you have 1 minute before it expires";
                MailService serv = new MailService();
                serv.sendFromGMail(from, pass, to, subject, body);

            }
            return true;

        }
        catch (SQLException ex) {
            printSQLException(ex);
            return false;
        }

    }

    private void printSQLException(SQLException ex) {
    }

    public boolean resetPwdCode(int code){
        if (random_int == code ){
            System.out.println("WELCOME");
            return true;
        }
        else {
            System.out.println("WRONG CODE PLEASE TRY AGAIN ");
            return false;
        }

    }
}
