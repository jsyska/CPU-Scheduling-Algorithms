package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    Process[] processes;

    static double fcfsAverageWaitingTime;
    static double fcfsAverageTurnAroundTime;

    static double sjfAverageWaitingTime;
    static double sjfAverageTurnAroundTime ;

    static double psjfAverageWaitingTime;
    static double psjfAverageTurnAroundTime;


    static double rotAverageWaitingTime;
    static double rotAverageTurnAroundTime;


    @FXML
    private TextField numberOfProcesses;

    public TextField getNumberOfProcesses() {
        return numberOfProcesses;
    }

    @FXML
    private TextField arrivalTime;

    @FXML
    private TextField burstTime;

    @FXML
    private TextField timeQuantum;

    public TextField getArrivalTime() {
        return arrivalTime;
    }

    public TextField getBurstTime() {
        return burstTime;
    }

    @FXML
    private Button generateDummyData;

    @FXML
    private Button calculateAndDraw;

    @FXML
    private TableView<Process> table;

    @FXML
    private TableColumn<Process,Integer> pidCol;

    @FXML
    private TableColumn<Process,Integer>  arrivalTimeCol;

    @FXML
    private TableColumn<Process,Integer> burstTimeCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        calculateAndDraw.setVisible(false);
        pidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        arrivalTimeCol.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        burstTimeCol.setCellValueFactory(new PropertyValueFactory<>("burstTime"));

    }


    @FXML
    void generateData(ActionEvent event) {
        processes = Scheduling.generateProcesses(Integer.valueOf(numberOfProcesses.getText().strip()),Integer.valueOf(arrivalTime.getText().strip()),Integer.valueOf(burstTime.getText().strip()));
        for(Process p: processes){
            table.getItems().add(p);
        }
        calculateAndDraw.setVisible(true);
    }


    //Change the scene
    public void changeScene(ActionEvent event){
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("Charts.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            //get the stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void calculate(ActionEvent event){
        int quantum = Integer.valueOf(timeQuantum.getText().strip());

        FCFS.calculateWaitingTime(processes);
        fcfsAverageWaitingTime = round(FCFS.getAverageWaitingTime(),3);
        fcfsAverageTurnAroundTime = round(FCFS.getAverageTurnAroundTime(),3);
        round(fcfsAverageTurnAroundTime,3);

        SJF.calculateWaitingTime(processes);
        sjfAverageWaitingTime = round(SJF.getAverageWaitingTime(),3);
        sjfAverageTurnAroundTime = round(SJF.getAverageTurnAroundTime(),3);


        PSJF.calculateWaitingTime(processes);
        psjfAverageWaitingTime = round(PSJF.getAverageWaitingTime(),3);
        psjfAverageTurnAroundTime = round(PSJF.getAverageTurnAroundTime(),3);

        rotAverageWaitingTime = round(Rotational.findAvgWaitingTime(processes,quantum),3);
        rotAverageTurnAroundTime = round(Rotational.findAvgTurnaAroundTime(processes,quantum),3);

        changeScene(event);

        printAverageTurnAroundTimes();

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public String printAverageTurnAroundTimes(){
        return "First come first seved: " + fcfsAverageTurnAroundTime
                +"\nShortest Job First: " + sjfAverageTurnAroundTime
                +"\nPreemptive Shortest Job First: " + psjfAverageTurnAroundTime
                +"\nRotational Algorithm: " + rotAverageTurnAroundTime;
    }

    public String printAverageWaitingTimes(){
        return "First come first seved: " + fcfsAverageWaitingTime
                +"\nShortest Job First: " + sjfAverageWaitingTime
                +"\nPreemptive Shortest Job First: " + psjfAverageWaitingTime
                +"\nRotational Algorithm: " + rotAverageWaitingTime;
    }

    public static double getFcfsAverageWaitingTime() {
        return fcfsAverageWaitingTime;
    }

    public static double getFcfsAverageTurnAroundTime() {
        return fcfsAverageTurnAroundTime;
    }

    public static double getSjfAverageWaitingTime() {
        return sjfAverageWaitingTime;
    }

    public static double getSjfAverageTurnAroundTime() {
        return sjfAverageTurnAroundTime;
    }

    public static double getPsjfAverageWaitingTime() {
        return psjfAverageWaitingTime;
    }

    public static double getPsjfAverageTurnAroundTime() {
        return psjfAverageTurnAroundTime;
    }

    public static double getRotAverageWaitingTime() {
        return rotAverageWaitingTime;
    }

    public static double getRotAverageTurnAroundTime() {
        return rotAverageTurnAroundTime;
    }

}
