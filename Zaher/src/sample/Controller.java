package sample;

import Entities.Users;
import Entities.category_freelance;
import Service.UserSession;

import Service.UsersDSService;
import animatefx.animation.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import javafx.scene.control.Alert.AlertType;
import util.DataSource;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller implements Initializable {
    private Connection cnx = DataSource.getInstance().getCnx();
    private ObservableList<Users> list;
    private ObservableList<category_freelance> listfrCat;







    @FXML
    private AnchorPane questionnairePane;

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private Label rootLbl;

    @FXML
    private ImageView logo;

    @FXML
    private Label pageLbl;

    @FXML
    private Button acceuilBtn;

    @FXML
    private Button freelaneBtn;

    @FXML
    private Button emploiBtn;

    @FXML
    private Button questionnaireBtn;

    @FXML
    private Button formationBtn;

    @FXML
    private Button reclamationBtn;

    @FXML
    private Button utilisateursBtn;

    @FXML
    private Button profileBtn;

    @FXML
    private Button parametresBtn;

    @FXML
    private Pane statusPane;

    @FXML
    private Button homeBtn;

    @FXML
    private Button closeBtn;

    @FXML
    private AnchorPane scenePane;

    @FXML
    private Label userName;
    @FXML
    private AnchorPane usersPane;

    @FXML
    private TableView<Users> tableUsers;

    @FXML
    private TableColumn<?, ?> idUser;

    @FXML
    private TableColumn<?, ?> nameUsers;

    @FXML
    private TableColumn<?, ?> prenomUsers;

    @FXML
    private TableColumn<?, ?> emailUsers;

    @FXML
    private TableColumn<?, ?> roleUsers;

    @FXML
    private TableColumn<Users, String> editUsers;

    @FXML
    private TableColumn<Users, String> deleteUsers;
    @FXML
    private AnchorPane freelancePane;

    @FXML
    private TableView<category_freelance> tableFreelanceCat;

    @FXML
    private TableColumn<?, ?> idfreelanceCat;

    @FXML
    private TableColumn<?, ?> namefreelanceCat;

    @FXML
    private TableColumn<?, ?> descfreelancecat;

    @FXML
    private TableColumn<?, ?> nbroffrefreelanceCat;

    @FXML
    private TableColumn<category_freelance, String> editfreelanceCat;

    @FXML
    private TableColumn<category_freelance, String> deletefreelanceCat;

    @FXML
    private PieChart rolesPieChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            populateTable();
            loadUsersPieChart();
            final Label caption = new Label("");
            caption.setTextFill(Color.WHITE);
            caption.setStyle("-fx-font: 12 arial;");

            for (final PieChart.Data data : rolesPieChart.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        caption.setTranslateX(e.getSceneX());
                        caption.setTranslateY(e.getSceneY());

                        caption.setText(String.valueOf(data.getPieValue()));
                    }
                });
            }
           // loadChart();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            userName.setText(UserSession.getUserName().toUpperCase());
            Notifications not = Notifications.create()
                    .title("CONNECTION")
                    .text("USER CONNECTED "+UserSession.getUserName().toUpperCase())
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            not.showInformation();
            rootLbl.setText("/WELCOME");
            pageLbl.setText(""+UserSession.getUserName().toUpperCase());
            statusPane.setVisible(true);
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(50, 172      ,250) , CornerRadii.EMPTY, Insets.EMPTY)) );
            new FadeInLeftBig(statusPane).play();

        }catch(Exception ex){
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



    private void loadUsersPieChart(){
        UsersDSService x = new UsersDSService();
        rolesPieChart.setTitle("USERS ROLES");
        rolesPieChart.setLabelLineLength(15);
        rolesPieChart.setLabelsVisible(false);
        rolesPieChart.setLegendSide(Side.BOTTOM);
        rolesPieChart.setStartAngle(0);
        rolesPieChart.setClockwise(false);
        rolesPieChart.setData(x.rolesStats());

    }

    @FXML
    private void handleClicks(ActionEvent event) throws SQLException {

        if(event.getSource() == acceuilBtn) {
        if ((pageLbl.getText().equals("Accueil")))
        {

            Notifications not = Notifications.create()
                    .title(pageLbl.getText())
                    .text("Tab Already Open")
                    .graphic(null)
                    .hideAfter(Duration.seconds(1))
                    .position(Pos.BOTTOM_RIGHT);
            not.showError();


        }
        else{
            usersPane.setVisible(false);
            freelancePane.setVisible(false);
            rootLbl.setText("/Accueil");
            pageLbl.setText("Accueil");
            statusPane.setVisible(true);
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(5    , 172      ,250) , CornerRadii.EMPTY, Insets.EMPTY)) );
            new FadeInLeftBig(statusPane).play();}



        }
        else if( event.getSource() == homeBtn) {
            if ((pageLbl.getText().equals("Accueil")))
            {

                Notifications not = Notifications.create()
                        .title(pageLbl.getText())
                        .text("Tab Already Open")
                        .graphic(null)
                        .hideAfter(Duration.seconds(1))
                        .position(Pos.BOTTOM_RIGHT);

                not.showError();

            }
            else{
            usersPane.setVisible(false);
            freelancePane.setVisible(false);
            rootLbl.setText("/Accueil");
            pageLbl.setText("Accueil");
            statusPane.setVisible(true);
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(5    , 172      ,250) , CornerRadii.EMPTY, Insets.EMPTY)) );
            new FadeInLeftBig(statusPane).play();}

        }
        else if (event.getSource() == freelaneBtn){
            if ((pageLbl.getText().equals("Freelance")))
            {

                Notifications not = Notifications.create()
                        .title(pageLbl.getText())
                        .text("Tab Already Open")
                        .graphic(null)
                        .hideAfter(Duration.seconds(1))
                        .position(Pos.BOTTOM_RIGHT);
                not.showError();


            }
            else{
                populateTablecatFr();
            usersPane.setVisible(false);
            freelancePane.setVisible(true);
            rootLbl.setText("/CategorieFreelance");
            pageLbl.setText("Freelance");
            statusPane.setVisible(true);
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(250   , 5  ,5) , CornerRadii.EMPTY, Insets.EMPTY)) );
            new FadeInLeftBig(statusPane).play();}
        }
        else if (event.getSource() == emploiBtn){
            if ((pageLbl.getText().equals("Emploi")))
            {
                Notifications not = Notifications.create()
                        .title(pageLbl.getText())
                        .text("Tab Already Open")
                        .graphic(null)
                        .hideAfter(Duration.seconds(1))
                        .position(Pos.BOTTOM_RIGHT);
                not.showError();


            }
            else{
            usersPane.setVisible(false);
            freelancePane.setVisible(false);
            rootLbl.setText("/CategorieEmploi");
            pageLbl.setText("Emploi");
            statusPane.setVisible(true);
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(215   , 62  ,15) , CornerRadii.EMPTY, Insets.EMPTY)) );
            new FadeInLeftBig(statusPane).play();}
        }
        else if (event.getSource() == questionnaireBtn){
            if ((pageLbl.getText().equals("Questionaires")))
            {
                Notifications not = Notifications.create()
                        .title(pageLbl.getText())
                        .text("Tab Already Open")
                        .graphic(null)
                        .hideAfter(Duration.seconds(1))
                        .position(Pos.BOTTOM_RIGHT);
                not.showError();


            }
            else{
            usersPane.setVisible(false);
            freelancePane.setVisible(false);
            rootLbl.setText("/CategorieQuestionnaire");
            pageLbl.setText("Questionaires");
            statusPane.setVisible(true);
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(215   , 189  ,15) , CornerRadii.EMPTY, Insets.EMPTY)) );
            new FadeInLeftBig(statusPane).play();}
        }
        else if (event.getSource() == formationBtn){
            if ((pageLbl.getText().equals("Formations")))
            {
                Notifications not = Notifications.create()
                        .title(pageLbl.getText())
                        .text("Tab Already Open")
                        .graphic(null)
                        .hideAfter(Duration.seconds(1))
                        .position(Pos.BOTTOM_RIGHT);
                not.showError();


            }
            else{
            usersPane.setVisible(false);
            freelancePane.setVisible(false);
            rootLbl.setText("/CategorieFormation");
            pageLbl.setText("Formations");
            statusPane.setVisible(true);
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(67  , 206 ,47) , CornerRadii.EMPTY, Insets.EMPTY)) );
            new FadeInLeftBig(statusPane).play(); }
        }

        else if (event.getSource() == reclamationBtn){
            if ((pageLbl.getText().equals("Reclamation")))
            {
                Notifications not = Notifications.create()
                        .title(pageLbl.getText())
                        .text("Tab Already Open")
                        .graphic(null)
                        .hideAfter(Duration.seconds(1))
                        .position(Pos.BOTTOM_RIGHT);
                not.showError();


            }
            else{
            usersPane.setVisible(false);
            freelancePane.setVisible(false);
            rootLbl.setText("/CategorieReclamation");
            pageLbl.setText("Reclamation");
            statusPane.setVisible(true);
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(93,13,49) , CornerRadii.EMPTY, Insets.EMPTY)) );
            new FadeInLeftBig(statusPane).play();}
        }
        else if (event.getSource() == utilisateursBtn){
            if ((pageLbl.getText().equals("Users")))
            {
                Notifications not = Notifications.create()
                        .title(pageLbl.getText())
                        .text("Tab Already Open")
                        .graphic(null)
                        .hideAfter(Duration.seconds(1))
                        .position(Pos.BOTTOM_RIGHT);
                not.showError();


            }
            else{
            usersPane.setVisible(true);
            freelancePane.setVisible(false);

            rootLbl.setText("/Users");
            pageLbl.setText("Users");
            statusPane.setVisible(true);
            freelancePane.setVisible(false);
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(68 ,97 ,255) , CornerRadii.EMPTY, Insets.EMPTY)) );
            new FadeInLeftBig(statusPane).play(); }
        }
        else if (event.getSource() == profileBtn){
            if ((pageLbl.getText().equals("Profile")))
            {
                Notifications not = Notifications.create()
                        .title(pageLbl.getText())
                        .text("Tab Already Open")
                        .graphic(null)
                        .hideAfter(Duration.seconds(1))
                        .position(Pos.BOTTOM_RIGHT);
                not.showError();


            }
            else{
                usersPane.setVisible(false);
                freelancePane.setVisible(false);

            rootLbl.setText("/Profile");
            pageLbl.setText("Profile");
            statusPane.setVisible(true);
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(43,60,99) , CornerRadii.EMPTY, Insets.EMPTY)) );
            new FadeInLeftBig(statusPane).play(); }
        }
        else if (event.getSource() == parametresBtn){

            if ((pageLbl.getText().equals("Parametres")))
            {
                Notifications not = Notifications.create()
                        .title(pageLbl.getText())
                        .text("Tab Already Open")
                        .graphic(null)
                        .hideAfter(Duration.seconds(1))
                        .position(Pos.BOTTOM_RIGHT);
                not.showError();


        }
            else {
                new FadeInLeftBig(statusPane).play();
                new FadeInRight(pageLbl).play();
                usersPane.setVisible(false);
                freelancePane.setVisible(false);
               // questionnairePane.setVisible(false);
                rootLbl.setText("/Parametres");
                pageLbl.setText("Parametres") ;
                statusPane.setVisible(true);
                statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(63,43,99) , CornerRadii.EMPTY, Insets.EMPTY)) );     }

        }
        }

     Stage stage;
    public void logout(ActionEvent actionEvent) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout");
        alert.setContentText("Do you want to save ? ");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("You logged out");
            Notifications not = Notifications.create()
                    .title(UserSession.getUserName().toUpperCase())
                    .text("SEE YOU SOON MR "+UserSession.getUserName().toUpperCase()+ " :D ! ")
                    .graphic(null)
                    .hideAfter(Duration.seconds(1))
                    .position(Pos.BOTTOM_RIGHT);
            not.showInformation();
            UserSession.cleanUserSession();



            stage.close();
        }
    }
    public int get() throws SQLException{
        int i2 = 0;
        UserSession n = UserSession.getInstance();
        System.out.println(n);
        /*String s1 = n.getUserName();
        System.out.println(s1);
        ResultSet rs1 =  cnx.createStatement().executeQuery("Select * from users where username = '"+s1+"' ");
        while (rs1.next()){
            i2 =rs1.getInt(1);
        }
        System.out.println("welcome mr "+i2);*/
        return i2;
    }
    private void populateTable() throws SQLException {
        list = FXCollections.observableArrayList();
        String query = "SELECT * FROM Users ";
        ResultSet set = cnx.createStatement().executeQuery(query);
        while(set.next()){
            Users u = new Users();
            u.setId(set.getInt("ID"));
            u.setUsername(set.getString("USERNAME"));
            u.setPrenom(set.getString("PRENOM"));
            u.setEmail(set.getString("EMAIL"));
            u.setRoles(set.getString("ROLES"));
            list.add(u);
        }
        // tableview properties
        idUser.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameUsers.setCellValueFactory(new PropertyValueFactory<>("Username"));
        prenomUsers.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        emailUsers.setCellValueFactory(new PropertyValueFactory<>("Email"));
        roleUsers.setCellValueFactory(new PropertyValueFactory<>("Roles"));

        Callback<TableColumn<Users,String>,TableCell<Users,String>> cellFactory = (manageUsers) -> {
            final TableCell<Users,String> cell = new TableCell<Users,String>(){
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //ensure the cell is created only in non empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    }
                    else {
                        // action button
                        final Button editButton = new Button("EDIT");
                        // btn listener     on clicked
                        editButton.setOnAction(event -> {
                            // get the clicked object
                            Users u = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setContentText("YOU'RE ABOUT TO DELETE THE USER WITH ID "+u.getId()+" CONFIRM ACTION ?");
                            alert.show();
                        });
                        setGraphic(editButton);
                        setText(null);
                    }
                }
            };

            //return the cell createrd
            return cell ;
        };
        Callback<TableColumn<Users,String>,TableCell<Users,String>> cellFactory2 = (manageUsers) -> {
            final TableCell<Users,String> cell = new TableCell<Users,String>(){
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //ensure the cell is created only in non empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    }
                    else {
                        // action button
                        final Button deleteButton = new Button("DELETE");
                        // btn listener     on clicked
                        deleteButton.setOnAction(event -> {
                            // get the clicked object
                            Users u = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setContentText("YOU'RE ABOUT TO DELETE THE USER WITH ID "+u.getId()+" CONFIRM ACTION ?");
                            alert.show();
                        });
                        setGraphic(deleteButton);
                        setText(null);
                    }
                }
            };

            //return the cell createrd
            return cell ;
        };
        editUsers.setCellFactory(cellFactory);
        deleteUsers.setCellFactory(cellFactory2);

         // tableview popluate with items
        tableUsers.setItems(list);

    }
    private void populateTablecatFr() throws SQLException {
        listfrCat = FXCollections.observableArrayList();
        String query2 = "SELECT * FROM category_freelance ";
        ResultSet set2 = cnx.createStatement().executeQuery(query2);
        while(set2.next()){
            category_freelance t = new category_freelance();
            t.setId(set2.getInt("ID"));
            t.setNom_cat_fr(set2.getString("nom_cat_fr"));
            t.setDescription_cat_fr(set2.getString("description_cat_fr"));
            t.setNbr_offre_fr(set2.getInt("nbr_offre_fr"));

            listfrCat.add(t);
        }
        // tableview properties
        idfreelanceCat.setCellValueFactory(new PropertyValueFactory<>("id"));
        namefreelanceCat.setCellValueFactory(new PropertyValueFactory<>("nom_cat_fr"));
        descfreelancecat.setCellValueFactory(new PropertyValueFactory<>("description_cat_fr"));
        nbroffrefreelanceCat.setCellValueFactory(new PropertyValueFactory<>("nbr_offre_fr"));


        Callback<TableColumn<category_freelance,String>,TableCell<category_freelance,String>> cellFactory1 = (manageFreelance) -> {
            final TableCell<category_freelance,String> cell2 = new TableCell<category_freelance,String>(){
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //ensure the cell is created only in non empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    }
                    else {
                        // action button
                        final Button editButton = new Button("DELETE");
                        // btn listener     on clicked
                        editButton.setOnAction(event -> {
                            // get the clicked object
                            category_freelance u = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setContentText("YOU'RE ABOUT TO DELETE THE CAT FR  WITH ID "+u.getId()+" CONFIRM ACTION ?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                String qry ="DELETE FROM category_freelance WHERE ID = ? ";
                                try {

                                    PreparedStatement preparedStmt = cnx.prepareStatement(qry);
                                    preparedStmt.setInt(1,u.getId());
                                    preparedStmt.execute();
                                    populateTablecatFr();


                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                            } else {
                                // ... user chose CANCEL or closed the dialog
                            }

                        });
                        setGraphic(editButton);
                        setText(null);
                    }
                }


            };

            //return the cell createrd
            return cell2 ;
        };
        Callback<TableColumn<category_freelance,String>,TableCell<category_freelance,String>> cellFactory2 = (manageFreelance) -> {
            final TableCell<category_freelance,String> cell2 = new TableCell<category_freelance,String>(){
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //ensure the cell is created only in non empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    }
                    else {
                        // action button
                        final Button deleteButton = new Button("EDIT");
                        // btn listener     on clicked
                        deleteButton.setOnAction(event -> {
                            // get the clicked object
                            category_freelance t = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setContentText("YOU'RE ABOUT TO DELETE THE CAT WITH ID "+t.getId()+" CONFIRM ACTION ?");
                            alert.show();
                        });
                        setGraphic(deleteButton);
                        setText(null);
                    }
                }
            };

            //return the cell createrd
            return cell2 ;
        };
        editfreelanceCat.setCellFactory(cellFactory1);
        deletefreelanceCat.setCellFactory(cellFactory2);

        // tableview popluate with items
        tableFreelanceCat.setItems(listfrCat);

    }


    private void loadChart(){
     /* String query = "SELECT nom_qst , nom_cat_qst from questionnaire";
        XYChart.Series series= new XYChart.Series<>();
        try{
            ResultSet rs = cnx.createStatement().executeQuery(query) ;
            while(rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getString(2)));
            }
            barChart.getData().add(series);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
    }

}
