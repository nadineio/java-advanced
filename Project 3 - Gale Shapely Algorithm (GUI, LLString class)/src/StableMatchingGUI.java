import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StableMatchingGUI extends Application {
    // Written by:
    // This program
    
    static TableView <Couple> tableOfMenPreferences, tableOfWomenPreferences;
    static TextField fileField;
    
    @Override
    public void start(Stage primaryStage) {
        Date currentDateTime = new Date();
        Label dateTime = new Label(currentDateTime.toString());
        dateTime.setFont(Font.font("Bookman Old Style", 
                                    FontWeight.SEMI_BOLD, 15));
        
        Label welcome = new Label("Welcome to Stable Matching!\n");
        welcome.setFont(Font.font("Bookman Old Style", 
                                   FontWeight.BOLD, FontPosture.REGULAR, 30));
        
        VBox dateFile = new VBox(0);
        dateFile.getChildren().addAll(dateTime, welcome);
        dateFile.setAlignment(Pos.CENTER);
        
        Label enterFileName = new Label
                              ("Please enter the file name for the input");
        enterFileName.setFont(Font.font("Bookman Old Style", 
                                         FontWeight.SEMI_BOLD, 15));
        
        fileField = new TextField();
        fileField.setPromptText("ex: data.txt");
        
        Button fileReadyBtn = new Button("Find File");
        fileReadyBtn.setOnAction(e -> {
            try {
                Match.readFile(primaryStage, fileField.getText());
            } catch (Exception ex) {
                Logger.getLogger(StableMatchingGUI.class.getName())
                      .log(Level.SEVERE, null, ex);
            }
            
        });
        BooleanBinding bindFile = Bindings.isEmpty(fileField.textProperty());
        fileReadyBtn.disableProperty().bind(bindFile);
        
        Button quitBtn = new Button("Exit");
        quitBtn.setOnAction(e -> primaryStage.close());
        
        HBox fileLayout = new HBox(10);
        fileLayout.getChildren().addAll(fileField, fileReadyBtn, quitBtn);
        fileLayout.setAlignment(Pos.CENTER);
        
        VBox fileInst = new VBox(5);
        fileInst.getChildren().addAll(enterFileName, fileLayout);
        fileInst.setAlignment(Pos.CENTER);
        
        VBox labelBtn = new VBox(30);
        labelBtn.getChildren().addAll(dateFile, fileInst);
        labelBtn.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(labelBtn, 500, 400);
        scene.getStylesheets().add(programSkin.class.getResource
                                    ("programSkin.css").toExternalForm());
        scene.setOnKeyPressed((final KeyEvent keyEvent) -> {
            if ((keyEvent.getCode() == KeyCode.ENTER) && 
                                        !(fileReadyBtn.isDisabled())) {
                try {
                    Match.readFile(primaryStage, fileField.getText());
                } catch (Exception ex) {
                    Logger.getLogger(StableMatchingGUI.class.getName())
                          .log(Level.SEVERE, null, ex);
                }
                keyEvent.consume();
            }
        });
        
        primaryStage.setTitle("Stable Matching - File Input");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    public static void match(Stage primaryStage) {
        Button menBtn = new Button("Make Men Propose!");
        menBtn.setOnAction(e -> Match.menPropose(primaryStage));
        
        Button womenBtn = new Button("Make Women Propose!");
        womenBtn.setOnAction(e -> Match.womenPropose(primaryStage));
        
        VBox buttonsLayout = new VBox(25);
        buttonsLayout.setAlignment(Pos.CENTER);
        buttonsLayout.getChildren().addAll(menBtn, womenBtn);
        
        Scene scene = new Scene(buttonsLayout, 500, 450);
        scene.setOnKeyPressed((final KeyEvent keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.M) 
                Match.menPropose(primaryStage);
            else if (keyEvent.getCode() == KeyCode.W)
                Match.womenPropose(primaryStage);
        });
        scene.getStylesheets().add(programSkin.class.getResource
                                    ("programSkin.css").toExternalForm());
        
        primaryStage.setTitle("Stable Matching");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void showCouples(Stage primaryStage) {
        String msg;
        msg = "";
                
        for (int i = 0; i < Match.num; i++) {
            msg += Match.husband.get(i) + "  ♥  " + Match.wife.get(i) + "\n";
        }
        
        Label couples = new Label(msg);
        couples.setAlignment(Pos.CENTER);
        couples.setFont(Font.font("Bookman Old Style", 
                                   FontWeight.BLACK, 20));
        
        Button quitBtn = new Button("Done");
        quitBtn.setOnAction(e -> primaryStage.close());
        
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> listPreferences(primaryStage));
        
        HBox buttons = new HBox(20);
        buttons.getChildren().addAll(backBtn, quitBtn);
        buttons.setAlignment(Pos.CENTER);
        
        VBox lblBtns = new VBox(50);
        lblBtns.getChildren().addAll(couples, buttons);
        lblBtns.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(lblBtns, 500, 400);
        scene.setOnKeyPressed((final KeyEvent keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) primaryStage.close();
        });
        scene.getStylesheets().add(programSkin.class.getResource
                                  ("programSkin.css").toExternalForm());
        
        primaryStage.setTitle("Matched Couples");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void listPreferences(Stage primaryStage) {
        menTable();
        womenTable();
        Label info = new Label(Match.info);
        info.setFont(Font.font("Bookman Old Style", 
                                   FontWeight.BOLD, 15));
        Button gsBtn = new Button("Perform Gale Shapely Algorithm");
        gsBtn.setOnAction(e -> match(primaryStage));
        VBox tables = new VBox(info, tableOfMenPreferences, 
                                     tableOfWomenPreferences, gsBtn);
        tables.setSpacing(15);
        tables.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(tables, 510, 490);
        scene.getStylesheets().add(programSkin.class.getResource
                                    ("programSkin.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void menTable() {
        TableColumn<Couple, String> menCol = new TableColumn<>("Man");
        menCol.setCellValueFactory(new PropertyValueFactory<>("man"));
        menCol.setMinWidth(60);
        
        TableColumn<Couple, String> firstChoiceCol = 
                        new TableColumn<>("1st Choice");
        firstChoiceCol.setCellValueFactory(
                        new PropertyValueFactory<>("firstChoice"));
        firstChoiceCol.setMinWidth(80);
        
        TableColumn<Couple, String> secondChoiceCol = 
                        new TableColumn<>("2nd Choice");
        secondChoiceCol.setCellValueFactory(
                        new PropertyValueFactory<>("secondChoice"));
        secondChoiceCol.setMinWidth(80);
        
        TableColumn<Couple, String> thirdChoiceCol = 
                        new TableColumn<>("3rd Choice");
        thirdChoiceCol.setCellValueFactory(
                        new PropertyValueFactory<>("thirdChoice"));
        thirdChoiceCol.setMinWidth(80);
        
        TableColumn<Couple, String> fourthChoiceCol = 
                        new TableColumn<>("4th Choice");
        fourthChoiceCol.setCellValueFactory(
                        new PropertyValueFactory<>("fourthChoice"));
        fourthChoiceCol.setMinWidth(80);
        
        TableColumn<Couple, String> fifthChoiceCol = 
                        new TableColumn<>("5th Choice");
        fifthChoiceCol.setCellValueFactory(
                        new PropertyValueFactory<>("fifthChoice"));
        fifthChoiceCol.setMinWidth(80);
        
        tableOfMenPreferences = new TableView<>();
        tableOfMenPreferences.setPrefSize(400, 147);
        tableOfMenPreferences.setItems(getMenEntry());
        tableOfMenPreferences.setColumnResizePolicy(
                              TableView.CONSTRAINED_RESIZE_POLICY);
        tableOfMenPreferences.getColumns().addAll(
                menCol, firstChoiceCol, secondChoiceCol, 
                thirdChoiceCol, fourthChoiceCol, fifthChoiceCol);
    }
    public static void womenTable() {
        TableColumn<Couple, String> womenCol = 
                        new TableColumn<>("Woman");
        womenCol.setCellValueFactory(
                        new PropertyValueFactory<>("man"));
        womenCol.setMinWidth(60);
        
        TableColumn<Couple, String> wfirstChoiceCol = 
                        new TableColumn<>("1st Choice");
        wfirstChoiceCol.setCellValueFactory(
                        new PropertyValueFactory<>("firstChoice"));
        wfirstChoiceCol.setMinWidth(80);
        
        TableColumn<Couple, String> wsecondChoiceCol = 
                        new TableColumn<>("2nd Choice");
        wsecondChoiceCol.setCellValueFactory(
                        new PropertyValueFactory<>("secondChoice"));
        wsecondChoiceCol.setMinWidth(80);
        
        TableColumn<Couple, String> wthirdChoiceCol = 
                        new TableColumn<>("3rd Choice");
        wthirdChoiceCol.setCellValueFactory(
                        new PropertyValueFactory<>("thirdChoice"));
        wthirdChoiceCol.setMinWidth(80);
        
        TableColumn<Couple, String> wfourthChoiceCol = 
                        new TableColumn<>("4th Choice");
        wfourthChoiceCol.setCellValueFactory(
                        new PropertyValueFactory<>("fourthChoice"));
        wfourthChoiceCol.setMinWidth(80);
        
        TableColumn<Couple, String> wfifthChoiceCol = 
                            new TableColumn<>("5th Choice");
        wfifthChoiceCol.setCellValueFactory(
                            new PropertyValueFactory<>("fifthChoice"));
        wfifthChoiceCol.setMinWidth(80);
        
        tableOfWomenPreferences = new TableView<>();
        tableOfWomenPreferences.setItems(getWomenEntry());
        tableOfWomenPreferences.setColumnResizePolicy(
                                TableView.CONSTRAINED_RESIZE_POLICY);
        tableOfWomenPreferences.setPrefSize(400, 147);
        tableOfWomenPreferences.getColumns().addAll(
                womenCol, wfirstChoiceCol, wsecondChoiceCol, 
                wthirdChoiceCol, wfourthChoiceCol, wfifthChoiceCol);
    }
    
    public static ObservableList<Couple> getMenEntry() {
        String man;
        int index; 
        
        Couple.menMatches = new Couple[Match.num];
        for (int i = 0; i < Match.num; i++) {
            man = Match.men.get(i);
            index = Match.men.indexOf(man);
            Couple.menMatches[i] = new Couple(man,
                    (Match.menPref.get(index * Match.num)), 
                    (Match.menPref.get((index * Match.num) + 1)), 
                    (Match.menPref.get((index * Match.num) + 2)), 
                    (Match.menPref.get((index * Match.num) + 3)), 
                    (Match.menPref.get((index * Match.num) + 4)));
        }
        ObservableList<Couple> couples = FXCollections.observableArrayList();
        for (int i = 0; i < Match.num; i++)
            couples.addAll(Couple.menMatches[i]);
        return couples;
    }
    
    public static ObservableList<Couple> getWomenEntry() {
        String woman;
        int index; 
        
        Couple.womenMatches = new Couple[Match.num];
        for (int i = 0; i < Match.num; i++) {
            woman = Match.women.get(i);
            index = Match.women.indexOf(woman);
            Couple.womenMatches[i] = new Couple(woman,
                    (Match.womenPref.get(index * Match.num)), 
                    (Match.womenPref.get((index * Match.num) + 1)), 
                    (Match.womenPref.get((index * Match.num) + 2)), 
                    (Match.womenPref.get((index * Match.num) + 3)), 
                    (Match.womenPref.get((index * Match.num) + 4)));
        }
        ObservableList<Couple> couples = FXCollections.observableArrayList();
        for (int i = 0; i < Match.num; i++)
            couples.addAll(Couple.womenMatches[i]);
        return couples;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}