package Service;

import Entities.Profile;
import javafx.collections.ObservableList;
import org.springframework.security.core.parameters.P;
import util.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileService {
    private Connection cnx = DataSource.getInstance().getCnx();

    public int formationNumber(int id ){
        String FORMATONS_QUERY ="select COUNT(*) from formation s inner join users u on s.users_id = "+id+" inner join categorie_formation cf on s.`Categorieformation_id` = cf.id group by s.id  ";

        int nb = 0 ;
        try  {
            ResultSet res = cnx.createStatement().executeQuery(FORMATONS_QUERY);
            while (res.next()){
               nb = res.getInt("count(*)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(nb);
        return nb;
    }
}
