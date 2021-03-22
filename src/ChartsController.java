import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChartsController implements Initializable {

    Controller controller = new Controller();

    @FXML
    private BarChart<?, ?> avgWaitingTimeChart;

    @FXML
    private BarChart<?, ?> avgTurnAroundTimeChart;

    @FXML
    private Button back;

    @FXML
    private Label avgWaitingTime;

    @FXML
    private Label avgTurnAroundTime;

    @FXML
    public void goBack(ActionEvent event){
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("assets/sample.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            //get the stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        avgTurnAroundTime.setText(controller.printAverageTurnAroundTimes());
        avgWaitingTime.setText(controller.printAverageWaitingTimes());
        XYChart.Series set1 = new XYChart.Series<>();
        set1.getData().add(new XYChart.Data("FCFS",Controller.getFcfsAverageWaitingTime()));
        set1.getData().add(new XYChart.Data("SJF",Controller.getSjfAverageWaitingTime()));
        set1.getData().add(new XYChart.Data("PSJF",Controller.getPsjfAverageWaitingTime()));
        set1.getData().add(new XYChart.Data("ROT",Controller.getRotAverageWaitingTime()));
        avgWaitingTimeChart.getData().addAll(set1);

        XYChart.Series set2 = new XYChart.Series<>();
        set2.getData().add(new XYChart.Data("FCFS",Controller.getFcfsAverageTurnAroundTime()));
        set2.getData().add(new XYChart.Data("SJF",Controller.getSjfAverageTurnAroundTime()));
        set2.getData().add(new XYChart.Data("PSJF",Controller.getPsjfAverageWaitingTime()));
        set2.getData().add(new XYChart.Data("ROT",Controller.getRotAverageTurnAroundTime()));
        avgTurnAroundTimeChart.getData().addAll(set2);


    }
}
