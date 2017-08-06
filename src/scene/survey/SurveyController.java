package scene.survey;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by User on 26/7/2017.
 */
public class SurveyController implements Initializable {

    @FXML
    private ToggleGroup rateEvent;
    @FXML
    private ToggleGroup rateVenue;
    @FXML
    private ToggleGroup hearEvent;
    @FXML
    private JFXTextField ans4;
    @FXML
    private JFXTextField ans5;
    @FXML
    private RadioButton radioexcellent1;
    @FXML
    private RadioButton radioexcellent;
    @FXML
    private RadioButton radionews;



    public enum RATE {
        Excellent("Excellent", 0), Good("Good", 1), Average("Average", 2), Poor("Poor", 3);

        private final String rate;
        private final int index;

        private RATE(String rate, int index) {
            this.index = index;
            this.rate = rate;

        }

        private int getIndex() {
            return index;
        }

        private String getRate() {
            return rate;
        }

    }

   public enum HEAR_EVENT {
        Newspaper("Newspaper"), NYPPortal("NYPPortal"), Radio("Radio"), Magazine("Magazine");
        private final String way;

        private HEAR_EVENT(String way) {
            this.way = way;
        }

        String getWay() {
            return way;
        }


    }


    public void clear(ActionEvent event) {
        ans5.clear();
        ans4.clear();
        rateEvent.selectToggle(null);
        hearEvent.selectToggle(null);
        rateVenue.selectToggle(null);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        radioexcellent.setSelected(true);
        radioexcellent1.setSelected(true);
        radionews.setSelected(true);


    }




    @FXML
    void submit(ActionEvent event) {

        if(ans4.getText().isEmpty() && (ans5.getText().isEmpty())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your response");
            alert.showAndWait();


        }
        else{
            RadioButton rb=(RadioButton)rateEvent.getSelectedToggle();
            RadioButton rv=(RadioButton)rateVenue.getSelectedToggle();

            RATE rEvent=getRate(rb.getText());
            RATE rVenue=getRate(rv.getText());


            RadioButton hb=(RadioButton)hearEvent.getSelectedToggle();
            HEAR_EVENT he=getHearEvent(hb.getText());




            String ans4txt = ans4.getText();
            String ans5txt = ans5.getText();

            SurveyQn sq=new SurveyQn(-1,rEvent,rVenue,he,ans4txt,ans5txt,"123456A");
            sq.save();

            /*Stage stage=(Stage)ans4.getScene().getWindow();
            stage.close();*/


        }




    }

    public static  RATE getRate(String text){
        if(text== RATE.Excellent.name()){return RATE.Excellent;}
        else if(text== RATE.Good.name()){return RATE.Good;}
        else if(text== RATE.Average.name()){return RATE.Average;}
        return RATE.Poor;

    }
    public static HEAR_EVENT getHearEvent(String text){
        if(text== HEAR_EVENT.Newspaper.getWay()){return HEAR_EVENT.Newspaper;}
        else if(text== HEAR_EVENT.NYPPortal.getWay()){return HEAR_EVENT.NYPPortal;}
        else if(text== HEAR_EVENT.Radio.getWay()){return HEAR_EVENT.Radio;}
        return HEAR_EVENT.Magazine;

    }




}
