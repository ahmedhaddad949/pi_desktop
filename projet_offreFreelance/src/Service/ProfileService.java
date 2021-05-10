package Service;

import Entities.Profile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.security.core.parameters.P;
import util.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileService {
    private Connection cnx = DataSource.getInstance().getCnx();


    public int formationNumber(String name ){
        String NAME_2_ID_QUERY = "SELECT * FROM USERS WHERE USERNAME = '"+name+"'";
        int nb = 0 ;
        try {

            ResultSet rs = cnx.createStatement().executeQuery(NAME_2_ID_QUERY);
            while (rs.next()){
                int id = rs.getInt("id");
                String NBR_FORMATONS_QUERY ="select COUNT(*) from formation s inner join users u on s.users_id = "+id+" inner join categorie_formation cf on s.`Categorieformation_id` = cf.id group by cf.nom_cat_frt  ";

                try  {
                    ResultSet res = cnx.createStatement().executeQuery(NBR_FORMATONS_QUERY);
                    while (res.next()){
                        nb = res.getInt("count(*)")-1;

                    } }
                    catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(nb);
        return nb;
    }



}
