package spellchecker;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SpellChecker extends Application {
    static final String      BKMN_FONT = "Bookman Old Style";
    static       int         langOpt;
    static       TextArea    taWrite, taErrors;
    static       Button      btnUpdFile, btnLoadDict, btnCheckDoc, 
                             btnCheckWr, btnRep, btnClose;
    static       VBox        vbTextUpdates, vbErrorsFile, vbRepErr, 
                             vbTextFmt, vbInput;
    static       HBox        hbInput, hbTextStyle, hbErrWrite;
    static       Label       lblWelcome, lblChooseInput;
    static       TextField   tfDictFile, tfDocFile;
    static       RadioButton rbWrite, rbFile;
    static       CheckBox    cbBold, cbItalic;
    static       HBox        hbLang, hbDict, hbDoc;
    static       VBox        vbOptions, vbFinLayout;
    
    @Override
    public void start(Stage primaryStage) {
        RadioButton rbArabic, rbEnglish, rbFrench;
        ToggleGroup tgLanguage, tgInputOpt;
        Scene       spellCheckScene;
        
        lblWelcome     = new Label();   
        lblChooseInput = new Label();
        
        rbWrite    = new RadioButton();
        rbFile     = new RadioButton();
        tgInputOpt = new ToggleGroup();
        
        rbArabic   = new RadioButton("عربي");
        rbEnglish  = new RadioButton("English");
        rbFrench   = new RadioButton("Français");
        tgLanguage = new ToggleGroup();
        
        taWrite     = new TextArea();
        taErrors    = new TextArea();
        tfDictFile  = new TextField();
        tfDocFile   = new TextField();
        btnLoadDict = new Button();
        btnCheckDoc = new Button();
        btnCheckWr  = new Button();
        btnRep      = new Button();
        btnUpdFile  = new Button();
        btnClose    = new Button();
        
        cbBold   = new CheckBox();
        cbItalic = new CheckBox();
        
        hbLang        = new HBox(20);
        hbDict        = new HBox(10);
        hbInput       = new HBox(10);
        hbDoc         = new HBox(10);
        hbTextStyle   = new HBox(10);
        hbErrWrite    = new HBox(10);
        vbRepErr      = new VBox(10);
        vbOptions     = new VBox(10);
        vbTextUpdates = new VBox(10);
        vbTextFmt     = new VBox(10);
        vbInput       = new VBox(10);
        vbErrorsFile  = new VBox(10);
        vbFinLayout   = new VBox(13);
        
        EventHandler<ActionEvent> handleStyle = e -> {
            if (cbBold.isSelected() && cbItalic.isSelected())
                taWrite.setFont(Font.font(BKMN_FONT, 
                                FontWeight.EXTRA_BOLD, 
                                FontPosture.ITALIC, 12));
            else if (cbBold.isSelected())
                taWrite.setFont(Font.font(BKMN_FONT, 
                                FontWeight.EXTRA_BOLD, 12)); 
            else if (cbItalic.isSelected())
                taWrite.setFont(Font.font(BKMN_FONT, 
                                FontPosture.ITALIC, 12)); 
            else 
                taWrite.setFont(Font.font(BKMN_FONT, 
                                FontWeight.NORMAL, 
                                FontPosture.REGULAR, 12)); 
        };

        EventHandler<ActionEvent> handleRbWrite = e -> {
            vbTextUpdates.getChildren().remove(Check.txtChecked);
            vbTextUpdates.getChildren().remove(Check.txtReplaced);
            vbTextFmt.getChildren().remove(btnUpdFile);
            vbTextFmt.getChildren().remove(hbTextStyle);
            vbTextFmt.getChildren().add(hbTextStyle);
            vbOptions.getChildren().remove(vbErrorsFile);
            hbErrWrite.getChildren().remove(vbRepErr);
            taWrite.clear();
            vbOptions.getChildren().add(hbErrWrite);
        };
        
        EventHandler<ActionEvent> handleRbFile = e -> {
            vbTextUpdates.getChildren().remove(Check.txtChecked);
            vbTextUpdates.getChildren().remove(Check.txtReplaced);
            vbOptions.getChildren().remove(hbErrWrite);
            vbOptions.getChildren().add(vbErrorsFile);
        };
        
        EventHandler<ActionEvent> handleRbLang = e -> {
            if (rbArabic.isSelected()) {
                langOpt = 1;
                SpellChecker.translate();
            } else if (rbEnglish.isSelected()) {
                langOpt = 2;
                SpellChecker.translate();
            } else if (rbFrench.isSelected()) {
                langOpt = 3;
                SpellChecker.translate();
            }
        };
        
        cbBold.setOnAction(handleStyle);
        cbItalic.setOnAction(handleStyle);
        rbWrite.setOnAction(handleRbWrite);
        rbFile.setOnAction(handleRbFile);
        rbArabic.setOnAction(handleRbLang);
        rbEnglish.setOnAction(handleRbLang);
        rbFrench.setOnAction(handleRbLang);
        btnLoadDict.setOnAction(e -> {
            try {
                Check.loadDict(tfDictFile.getText());
            } catch (FileNotFoundException ex) {
            }
        });
        btnCheckDoc.setOnAction(e -> {
            try {
                Check.spellCheck(Check.readDoc(tfDocFile.getText()), 1);
            } catch (FileNotFoundException ex) {
            }
        });
        btnCheckWr.setOnAction(e -> Check.spellCheck(taWrite.getText(), 2));
        btnRep.setOnAction(e -> Check.showReplacements());
        btnUpdFile.setOnAction(e -> {
            try {
                Check.updateFile(tfDocFile.getText());
            } catch (FileNotFoundException ex) {
            }
        });
        btnClose.setOnAction(e -> primaryStage.close());
        
        lblWelcome.setFont(Font.font(BKMN_FONT, FontWeight.EXTRA_BOLD, 50));
        lblChooseInput.setFont(Font.font(BKMN_FONT, FontWeight.BOLD,   20));
        
        tfDictFile.setPromptText("dictionary.txt");
        tfDocFile.setPromptText("document.txt");
        
        taWrite.setMinSize(280, 175);
        taErrors.setMinSize(200, 175);
        
        taWrite.setMaxSize(280, 175);
        taErrors.setMaxSize(200, 175);
        
        taWrite.setWrapText(true);
        taErrors.setWrapText(true);
        
        tgLanguage.getToggles().addAll(rbArabic, rbEnglish, rbFrench);
        tgInputOpt.getToggles().addAll(rbWrite, rbFile);
        hbLang.getChildren().addAll(rbArabic, rbEnglish, rbFrench);
        hbDict.getChildren().addAll(tfDictFile, btnLoadDict);
        hbInput.getChildren().addAll(lblChooseInput, vbInput);
        hbDoc.getChildren().addAll(tfDocFile, btnCheckDoc);
        vbErrorsFile.getChildren().add(hbDoc);
        vbTextFmt.getChildren().addAll(taWrite, hbTextStyle);
        vbRepErr.getChildren().addAll(taErrors, btnRep);
        hbErrWrite.getChildren().add(vbTextFmt);
        vbInput.getChildren().addAll(rbWrite, rbFile);
        hbTextStyle.getChildren().addAll(cbBold, cbItalic, btnCheckWr);
        vbFinLayout.getChildren().addAll(hbLang, lblWelcome, hbInput, hbDict, 
                                         vbOptions, vbTextUpdates, btnClose);
        
        hbLang.setAlignment(Pos.CENTER);
        hbDict.setAlignment(Pos.CENTER);
        hbInput.setAlignment(Pos.CENTER);
        hbDoc.setAlignment(Pos.CENTER);
        vbTextFmt.setAlignment(Pos.CENTER);
        hbErrWrite.setAlignment(Pos.CENTER);
        vbErrorsFile.setAlignment(Pos.CENTER);
        vbRepErr.setAlignment(Pos.CENTER);
        hbTextStyle.setAlignment(Pos.CENTER);
        vbTextUpdates.setAlignment(Pos.CENTER);
        vbFinLayout.setAlignment(Pos.CENTER);
        
        btnLoadDict.disableProperty().bind(Bindings.isEmpty(
                tfDictFile.textProperty()).or(Bindings.not(
                        Bindings.or(rbArabic.selectedProperty(), 
                                    rbEnglish.selectedProperty())
                                .or(rbFrench.selectedProperty()))));
        btnCheckDoc.disableProperty().bind(
                    Bindings.isEmpty(tfDictFile.textProperty()).or(
                    Bindings.isEmpty(tfDocFile.textProperty())));
        btnCheckWr.disableProperty().bind(
                    Bindings.isEmpty(tfDictFile.textProperty()).or(
                    Bindings.isEmpty(taWrite.textProperty())));
        
        rbEnglish.requestFocus();
        rbEnglish.setSelected(true);
        initialize(2);
        
        spellCheckScene = new Scene(vbFinLayout, 540, 650);
        spellCheckScene.getStylesheets().add(programSkin.class.getResource
                                  ("programSkin.css").toExternalForm());
        primaryStage.setTitle("Spell Checker");
        primaryStage.setScene(spellCheckScene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void initialize(int lang) {
        langOpt = lang;
        translate();
    }
    
    public static void translate() {
        if (langOpt == 1) {
            lblWelcome.setText("مدقق إملائي");
            lblChooseInput.setText("أريد التحقق من...");
            rbWrite.setText("كـتابتي");
            rbFile.setText("ملفي");
            btnClose.setText("إغلاق");
            btnLoadDict.setText("حمل القاموس");
            btnCheckDoc.setText("دقق");
            btnCheckWr.setText("دقق");
            btnRep.setText("استبدل");
            taWrite.setPromptText("اكتب هنا ثم اضغط على زر التدقيق...");
            btnUpdFile.setText("حدث الملف");
            cbBold.setText("عريض");
            cbItalic.setText("مائل");
            
            btnCheckDoc.setMinWidth(85);
            btnCheckDoc.setMaxWidth(85);
            
            hbTextStyle.getChildren().clear();
            hbTextStyle.getChildren().addAll(btnCheckWr, cbItalic, cbBold);
            
            hbInput.getChildren().remove(lblChooseInput);
            hbInput.getChildren().add(lblChooseInput);
            
            hbDict.getChildren().remove(tfDictFile);
            hbDict.getChildren().add(tfDictFile);
            
            hbDoc.getChildren().remove(tfDocFile);
            hbDoc.getChildren().add(tfDocFile);
            
        } else if (langOpt == 2) {
            lblWelcome.setText("Spell Checker");
            lblChooseInput.setText("I would like to...");
            rbWrite.setText("spell-check my writing");
            rbFile.setText("spell-check my file");
            btnClose.setText("Close");
            btnLoadDict.setText("Load Dictionary");
            btnCheckDoc.setText("Check Spelling");
            btnCheckWr.setText("Check Spelling");
            btnRep.setText("Replace Text");
            taWrite.setPromptText("Type here then click the button to check spelling...");
            btnUpdFile.setText("Update File");
            cbBold.setText("Bold");
            cbItalic.setText("Italic");
            
            btnCheckDoc.setMinWidth(103);
            btnCheckDoc.setMaxWidth(103);
            
            hbTextStyle.getChildren().clear();
            hbTextStyle.getChildren().addAll(cbBold, cbItalic, btnCheckWr);
            
            hbInput.getChildren().clear();
            hbInput.getChildren().addAll(lblChooseInput, vbInput);
            
            hbDict.getChildren().clear();
            hbDict.getChildren().addAll(tfDictFile, btnLoadDict);
            
            hbDoc.getChildren().clear();
            hbDoc.getChildren().addAll(tfDocFile, btnCheckDoc);
            
        } else if (langOpt == 3) {
            lblWelcome.setText("Correcteur\nOrthographique");
            lblChooseInput.setText("Je voudrais...");
            rbWrite.setText("vérifier l'orthographe de mon écriture");
            rbFile.setText("vérifier l'orthographe de mon fichier");
            btnClose.setText("Fermer");
            btnLoadDict.setText("Charger le dictionnaire");
            btnCheckDoc.setText("Vérifier l'orthographe");
            btnCheckWr.setText("Vérifier l'orthographe");
            btnRep.setText("Remplacer le texte");
            taWrite.setPromptText("Tapez ici puis cliquez le bouton pour vérifier l'orthographe ...");
            btnUpdFile.setText("Fichier de mise à jour");
            cbBold.setText("Gras");
            cbItalic.setText("Italique");
            
            btnCheckDoc.setMinWidth(140);
            btnCheckDoc.setMaxWidth(140);
            
            hbTextStyle.getChildren().clear();
            hbTextStyle.getChildren().addAll(cbBold, cbItalic, btnCheckWr);
            
            hbInput.getChildren().clear();
            hbInput.getChildren().addAll(lblChooseInput, vbInput);
            
            hbDict.getChildren().clear();
            hbDict.getChildren().addAll(tfDictFile, btnLoadDict);
            
            hbDoc.getChildren().clear();
            hbDoc.getChildren().addAll(tfDocFile, btnCheckDoc);
            
        }
        Check.selectLang();
            
    }
    
}
