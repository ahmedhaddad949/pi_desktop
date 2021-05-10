package Service;


import Entities.UsersStats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import util.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersDSService {
    private  Connection cnx = DataSource.getInstance().getCnx();

    private static final String ROLES_ST_QUERY = "select roles, count(*) from users group by roles  ;";

    public ObservableList<PieChart.Data> rolesStats(){
        ObservableList<PieChart.Data> myList = FXCollections.observableArrayList();
        try {
            ResultSet res = cnx.createStatement().executeQuery(ROLES_ST_QUERY);
            while (res.next()) {
                PieChart.Data c = new PieChart.Data(res.getString("ROLES"),res.getInt("count(*)"));
                myList.add(c);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(myList);

        return myList;
    }
}
