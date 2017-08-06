package scene.report;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import scene.survey.SurveyQn;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by User on 6/8/2017.
 */
public class ViewReportDetailController implements Initializable {
    @FXML
    private VBox vBoxTable;

    @FXML
    private Label rateEventF;

    @FXML
    private Label reportqn2;

    @FXML
    private Label reportqn3;

    @FXML
    private Label reportqn4;

    @FXML
    private Label reportqn5;

    @FXML
    private Label rateVenueF;

    @FXML
    private Label hearEventF;

    @FXML
    private Label q1F;

    @FXML
    private Label q2F;

    @FXML
    void close() {
        ((Stage)vBoxTable.getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setSurvey(SurveyQn s) {

        rateEventF.setText(s.getEventRate().name());
        rateVenueF.setText(s.getVenueRate().name());
        hearEventF.setText(s.getHe().name());
        q1F.setText(s.getAns4());
        q2F.setText(s.getAns5());


    }


    }



