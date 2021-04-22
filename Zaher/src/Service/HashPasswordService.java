package Service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashPasswordService {


    public String Hashpwd(String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("HASHED PW : "+hashed);
        return hashed;

    }
}
