package carwash;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CarWashGUI extends Application {
    //Written by: Nadine Mansour and Ahmed Cetiner
    //This program implements a car wash simulation using a GUI.

    static final String    BKMN_FONT = "Bookman Old Style";
    static       TextField tfShiftPeriod, tfCarsWaiting;
    static       TextArea  taUpdate, taReport;
    static       Button    btnGo;
    static       boolean   isMin;
    
    @Override
    public void start(Stage primaryStage) {
        Label       lblWelcome, lblUpdates, lblResults;
        RadioButton rbHrs, rbMin;
        ToggleGroup tgTimeOpt;
        HBox        hRbsLayout, hDurLayout, hInfLayout;
        VBox        vDurLayout, vUpdLayout, vRepLayout, vFinLayout;
        Scene       shiftScene;
        
        lblWelcome = new Label("Welcome to\nEthan's Car Wash");
        lblUpdates = new Label("UPDATES");
        lblResults = new Label("REPORT");
        
        rbHrs     = new RadioButton("hrs.");
        rbMin     = new RadioButton("mins.");
        tgTimeOpt = new ToggleGroup();
        
        tfShiftPeriod = new TextField();
        tfCarsWaiting = new TextField();
        taUpdate      = new TextArea();
        taReport      = new TextArea();
        btnGo         = new Button("Start Shift");
        
        hRbsLayout = new HBox(10);
        hDurLayout = new HBox(10);
        hInfLayout = new HBox();
        
        vDurLayout = new VBox(10);
        vUpdLayout = new VBox();
        vRepLayout = new VBox();
        vFinLayout = new VBox(30);
        
        shiftScene = new Scene(vFinLayout, 500, 800);
        
        lblWelcome.setFont(Font.font(BKMN_FONT,  FontWeight.EXTRA_BOLD, 50));
        lblUpdates.setFont(Font.font("Courrier", FontWeight.BOLD,       20));
        lblResults.setFont(Font.font("Courrier", FontWeight.BOLD,       20));
        lblUpdates.setAlignment(Pos.CENTER);
        lblResults.setAlignment(Pos.CENTER);
        
        tgTimeOpt.getToggles().addAll(rbHrs, rbMin);
        rbHrs.setOnAction(e -> isMin = false);
        rbMin.setOnAction(e -> isMin = true);
        btnGo.setOnAction(e -> Simulate.runShift(primaryStage));
        btnGo.disableProperty().bind(Bindings.or(Bindings.isEmpty(
                tfCarsWaiting.textProperty()), 
                Bindings.isEmpty(tfShiftPeriod.textProperty())).
                or(Bindings.not(Bindings.or(rbHrs.selectedProperty(), 
                                            rbMin.selectedProperty()))));
        
        tfShiftPeriod.setMinHeight(25);
        tfCarsWaiting.setMinHeight(25);
        tfShiftPeriod.setMaxSize(100, 30);
        tfCarsWaiting.setMaxSize(100, 30);
        tfShiftPeriod.setPromptText("shift duration");
        tfCarsWaiting.setPromptText("cars waiting");
        
        taUpdate.setEditable(false);
        taReport.setEditable(false);
        taUpdate.setMinSize(190, 250);
        taReport.setMinSize(210, 250);
        taUpdate.setMaxSize(190, 250);
        taReport.setMaxSize(210, 250);
        taUpdate.setPromptText("Minute-by-minute updates will "
                             + "appear here when the shift begins.");
        taReport.setPromptText("Click on \"Get Report\" at the end of "
                             + "the simulation to see a brief report "
                             + "from the simulated shift.");
        
        hRbsLayout.getChildren().addAll(rbHrs, rbMin);
        vDurLayout.getChildren().addAll(tfShiftPeriod, hRbsLayout);
        hDurLayout.getChildren().addAll(vDurLayout, tfCarsWaiting, btnGo);
        vUpdLayout.getChildren().addAll(lblUpdates, taUpdate);
        vRepLayout.getChildren().addAll(lblResults, taReport);
        hInfLayout.getChildren().addAll(vUpdLayout, vRepLayout);
        vFinLayout.getChildren().addAll(lblWelcome, hDurLayout, hInfLayout);
        
        hRbsLayout.setAlignment(Pos.CENTER);
        vDurLayout.setAlignment(Pos.CENTER);
        hDurLayout.setAlignment(Pos.TOP_CENTER);
        vUpdLayout.setAlignment(Pos.CENTER);
        vRepLayout.setAlignment(Pos.CENTER);
        hInfLayout.setAlignment(Pos.CENTER);
        vFinLayout.setAlignment(Pos.CENTER);
        
        vUpdLayout.setPadding(new Insets(10));
        vRepLayout.setPadding(new Insets(10));
        
        shiftScene.getStylesheets().add(programSkin.class.getResource
                                  ("programSkin.css").toExternalForm());
        primaryStage.setTitle("Ethan's Car Wash Simulation");
        primaryStage.setScene(shiftScene);
        primaryStage.show();
    }
    
    public static int getDuration() {
        int duration;
        duration = Integer.parseInt(tfShiftPeriod.getText());
        if (isMin) {
            tfShiftPeriod.setText(duration + " mins.");
            Simulate.durationUnits = "minute";
        } else {
            tfShiftPeriod.setText(duration + " hrs.");
            Simulate.durationUnits = "hour";
            duration *= 60;
        }
        return duration;
    }
    
    public static void setPermanentText(int carsWaiting) {
        if (carsWaiting == 1)
            tfCarsWaiting.setText(carsWaiting + " car waiting");
        else
            tfCarsWaiting.setText(carsWaiting + " cars waiting");
        tfShiftPeriod.setEditable(false);
        tfCarsWaiting.setEditable(false);
    }
    
    public static void close(Stage primaryStage) {
        primaryStage.close();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
