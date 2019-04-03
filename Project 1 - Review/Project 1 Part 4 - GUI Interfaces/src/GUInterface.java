import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GUInterface extends Application {
    // Written by: Nadine Mansour
    // Creates a graphical user interface

    private final String BLUE_PATTERN_URL    = "blue-pat.jpg";
    private final String YELLOW_PATTERN_URL  = "yellow-pat.jpg";
    private final String GREEN_PATTERN_URL   = "green-pat.jpg";
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("EECS 1570");
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(200);
        
        Image redPat    = new Image(BLUE_PATTERN_URL);
        Image yellowPat = new Image(YELLOW_PATTERN_URL);
        Image bluePat   = new Image(GREEN_PATTERN_URL);
        
        ImagePattern b = new ImagePattern(redPat,    1, 1, 200, 200, false);
        ImagePattern y = new ImagePattern(yellowPat, 1, 1, 150, 150, false);
        ImagePattern g = new ImagePattern(bluePat,   1, 1, 100,  50, false);
        
        Rectangle blueR     = new Rectangle(230, 150,   b);
        Rectangle yellowR   = new Rectangle(270, 150,   y);
        Rectangle greenR    = new Rectangle(500,  50,   g);
        
        HBox upperRect  = new HBox(0, blueR, yellowR);
        VBox allRect    = new VBox(0, upperRect, greenR);
        
        Button javaBtn  = new Button("Java");
        Button prgmgBtn = new Button("Programming");
        Button easyBtn  = new Button("Is not so easy.");
        Button ldsBtn   = new Button("Linear Data Structures!");
        
        HBox pinkBtns   = new HBox(20, javaBtn, prgmgBtn);
        HBox upperBtns  = new HBox(100, pinkBtns, easyBtn);
        VBox allBtns    = new VBox(120, upperBtns, ldsBtn);
        StackPane root  = new StackPane();
        
        javaBtn.setStyle("-fx-font-weight: bold;");
        prgmgBtn.setStyle("-fx-font-weight: bold;");
        easyBtn.setStyle("-fx-font-weight: bold;");
        ldsBtn.setStyle("-fx-font-weight: bold;");
        ldsBtn.setStyle("-fx-border-color: #009999; -fx-border-width: 2px;");
        
        javaBtn.setMinWidth(60);
        prgmgBtn.setMinWidth(110);
        easyBtn.setMinWidth(125);
        ldsBtn.setMinWidth(170);
        
        pinkBtns.setPadding(new Insets(0, 0, 0, 20));
        allBtns.setAlignment(Pos.CENTER);
        root.getChildren().addAll(allRect, allBtns);
        
        Scene guiPage = new Scene(root, 500, 200);
        primaryStage.setScene(guiPage);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}