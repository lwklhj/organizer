package scene.report;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import resources.database.DB;
import scene.survey.SurveyController;
import scene.survey.SurveyQn;

import javax.sql.rowset.CachedRowSet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by User on 1/8/2017.
 */
public class ReportController implements Initializable {
    @FXML
    private ListView<SurveyQn> reportListView;
    ObservableList<SurveyQn> reportList = FXCollections.observableArrayList();

    //ArrayList<SurveyQn> dataList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    private void update() {
        CachedRowSet data = DB.read("select * from surveyTable ");
        try {
            while (data.next()) {
                SurveyQn sq = new SurveyQn();
                sq.setId(data.getInt("id"));

                // get eventrate
                sq.setEventRate(SurveyController.getRate(data.getString("Question1")));

                //get venueRate
                sq.setVenueRate(SurveyController.getRate(data.getString("Question2")));
                //he
                sq.setHe(SurveyController.getHearEvent(data.getString("Question3")));
                sq.setAns4(data.getString("Question4"));
                sq.setAns5(data.getString("Question5"));
                sq.setUserID(data.getString("userID"));
                // ,data.getString(1),data.getString(2),
                //          data.getString(0));
                reportList.add(sq);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        reportListView.setItems(reportList);
        reportListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {

                    ObservableList<SurveyQn> data = reportListView.getSelectionModel().getSelectedItems();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewReportDetail.fxml"));
                    Parent root = loader.load();
                    ViewReportDetailController ctl = loader.getController();
                    ctl.setSurvey(data.get(0));


                    Stage stage = new Stage();
                    stage.setTitle("Report Details");
                    stage.setScene(new Scene(root));
                    stage.show();
                    reportListView.getSelectionModel().clearSelection();
                } catch (NullPointerException e) {
                } catch (IOException e) {
                }

            }
        });


    }

    @FXML
    void retrive(ActionEvent event) {
        reportListView.getItems().clear();
        reportList.clear();
        update();

    }


}
