package Service;
import Entities.Users ;

import java.util.ArrayList;

public interface IServiceUtilisateur extends IService<Users>{
    public ArrayList<Users> selectByName(String nom);
    ArrayList<Users> selectAllEnabled();
    public Users selectOne(String username);
}