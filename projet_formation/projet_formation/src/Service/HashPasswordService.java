package Service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashPasswordService {
private static int workload = 12 ;

    public String Hashpwd(String password) {
        String salt = BCrypt.gensalt(workload);
        String hashed = BCrypt.hashpw(password,salt);
        System.out.println("HASHED PW : "+hashed);
        return hashed;

    }
    public static boolean checkPassword(String password , String stored_hash) {
        boolean password_verified = false;

        password_verified = BCrypt.checkpw(password, stored_hash);

        return(password_verified);
    }
}
