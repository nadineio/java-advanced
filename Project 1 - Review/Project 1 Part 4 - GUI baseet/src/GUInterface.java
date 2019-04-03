import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GUInterface extends Application {
    // Written by: Nadine Mansour
    // Creates a graphical user interface

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("EECS 1570");
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(200);
        
        Rectangle pinkR     = new Rectangle(230, 150,   Color.PINK);
        Rectangle yellowR   = new Rectangle(270, 150,   Color.YELLOW);
        Rectangle blueR    = new Rectangle(500,  50,   Color.BLUE);
        
        HBox upperRect  = new HBox(0, pinkR, yellowR);
        VBox allRect    = new VBox(0, upperRect, blueR);
        
        Button javaBtn  = new Button("Java");
        Button prgmgBtn = new Button("Programming");
        Button easyBtn  = new Button("Is not so easy.");
        Button ldsBtn   = new Button("Linear Data Structures!");
        
        HBox pinkBtns   = new HBox(20, javaBtn, prgmgBtn);
        HBox upperBtns  = new HBox(100, pinkBtns, easyBtn);
        VBox allBtns    = new VBox(120, upperBtns, ldsBtn);
        StackPane root  = new StackPane();
        
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