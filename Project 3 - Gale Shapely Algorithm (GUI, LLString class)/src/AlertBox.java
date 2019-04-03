import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AlertBox {
    public static void display(String title, String message) {
        Stage primaryStage = new Stage();
        primaryStage.setTitle(title);
        
        Label alert = new Label(message);
        alert.setFont(Font.font("Bookman Old Style", FontWeight.BOLD, 15));
        
        Button closeBtn = new Button("OK");
        closeBtn.setOnAction(e -> primaryStage.close());

        VBox alertLayout = new VBox(20);
        alertLayout.setPadding(new Insets(10));
        alertLayout.getChildren().addAll(alert, closeBtn);
        alertLayout.setAlignment(Pos.CENTER);

        Scene showAlert = new Scene(alertLayout);
        showAlert.getStylesheets().add(programSkin.class.getResource
                                      ("programSkin.css").toExternalForm());
        showAlert.setOnKeyPressed((final KeyEvent keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                primaryStage.close();
                keyEvent.consume();
        }});
        
        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(200);
        primaryStage.setScene(showAlert);
        primaryStage.showAndWait();
    }
}