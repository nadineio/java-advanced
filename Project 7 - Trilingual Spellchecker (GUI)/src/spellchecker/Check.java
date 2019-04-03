package spellchecker;

import hashtable.HashTable;
import hashtable.HashTableAr;
import hashtable.HashTableFr;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import javafx.scene.text.Text;

public class Check {
    static HashTable htDictionary = new HashTable();
    static Text      txtLang, txtLoaded, txtChecked, txtReplaced, txtUpdated;
    static String    newText;
    static int       input;
    static String    dictLoaded, dictNotFound, spellingChecked, ldDictFrst, 
                     fileUpd, noFileExists, noRepl, repl, noSugFound, 
                     message, suggestions, checkInput, arrow;
    
    
    public static void selectLang() {
        SpellChecker.vbTextUpdates.getChildren().clear();
        switch (SpellChecker.langOpt) {
            case 1:
                txtLang         = new Text("تم اختيار اللغة العربية.");
                dictLoaded      = "تم تحميل القاموس.";
                dictNotFound    = "عفوا لم يتم العثور على ملف القاموس.";
                spellingChecked = "لقد تم التدقيق الإملائي.";
                ldDictFrst      = "يرجى تحميل ملف القاموس قبل التدقيق.";
                fileUpd         = "تم تحديث الملف.";
                noFileExists    = "الملف غير موجود.";
                noRepl          = "لا يوجد شئ للإستبدال.";
                repl            = "تم إستبدال الكلمات الخاطئة";
                noSugFound      = "لا يوجد إقتراح.";
                message         = "عدد الأخطاء";
                suggestions     = "الأخطاء >>> الأقتراحات";
                checkInput      = "يرجى التحقق من الإدخال.";
                arrow           = " <<< ";
                break;
            case 2:
                txtLang         = new Text("English selected.");
                dictLoaded      = "Dictionary successfully loaded.";
                dictNotFound    = "Dictionary file not found. Check again.";
                spellingChecked = "Spelling Checked.";
                ldDictFrst      = "ERROR: Please load a dictionary before checking input.";
                noFileExists    = "Document does not exist.";
                noRepl          = "Nothing to replace.";
                repl            = "Text Replaced.";
                noSugFound      = "No replacement found";
                message         = "Number of Errors";
                suggestions     = "Errors >>> Suggestions";
                checkInput      = "ERROR: Please check input.";
                arrow           = " >>> ";
                break;
            case 3:
                txtLang = new Text("Le français a été sélectionné.");
                dictLoaded      = "Dictionnaire chargé.";
                dictNotFound    = "Fichier de dictionnaire introuvable. Revérifier.";
                spellingChecked = "Orthographe vérifiée.";
                ldDictFrst      = "ERREUR: Veuillez charger un dictionnaire avant de vérifier l'entrée.";
                noFileExists    = "Le document n'existe pas.";
                noRepl          = "Rien à remplacer.";
                repl            = "Texte remplacé.";
                noSugFound      = "Aucun remplacement trouvé";
                message         = "Nombre d'erreurs";
                suggestions     = "Les erreurs >>> Les suggestions";
                checkInput      = "ERREUR: Veuillez vérifier l'entrée.";
                arrow           = " >>> ";
                break;
            default:
                txtLang = new Text("Please select language of input.");
                break;
        }
        resetLayout();
        txtLang.setId("updateStyle");
        SpellChecker.vbTextUpdates.getChildren().add(txtLang);
    }
    
    public static void loadDict(String fileName) throws FileNotFoundException {
        File    dictFile;
        Scanner fileIn;
        String  dictTxt;
        int     htSize;
        
        dictTxt = "";
        
        if (!fileName.endsWith(".txt")) 
            fileName += ".txt";
        dictFile = new File(fileName);
                
        if (dictFile.exists()) {
            fileIn = new Scanner(dictFile, "UTF-8");
            while (fileIn.hasNext())
                dictTxt += fileIn.next() + " ";
            htSize = getArraySize(dictTxt);
            fileIn = new Scanner(dictTxt);
             
            switch (SpellChecker.langOpt) {
                case 1:  htDictionary = new HashTableAr(htSize);  break;
                case 2:  htDictionary = new HashTable(htSize);    break;
                case 3:  htDictionary = new HashTableFr(htSize);  break;
                default:                                          break;
            }
            while (fileIn.hasNext()) 
                htDictionary.insert(fileIn.next());
            
            SpellChecker.taErrors.clear();
            SpellChecker.vbTextUpdates.getChildren().clear();
            SpellChecker.vbTextUpdates.getChildren().add(txtLang);
            txtLoaded = new Text(dictLoaded);
            txtLoaded.setId("updateStyle");
            SpellChecker.vbTextUpdates.getChildren().add(txtLoaded);
            resetLayout();
        } else {
            SpellChecker.vbTextUpdates.getChildren().clear();
            txtLoaded = new Text(dictNotFound);
            txtLoaded.setId("updateStyle");
            SpellChecker.vbTextUpdates.getChildren().add(txtLoaded);
        }
    }
    
    public static void spellCheck(String checkStr, int pick) {
        Scanner  readStr;
        String[] text, errors;
        int      numWords, numErrors, indexAdd, indexCheck, indexError;
        
        numWords   = 0;
        numErrors  = 0;
        indexAdd   = 0;
        indexCheck = 0;
        indexError = 0;
        readStr    = new Scanner(checkStr);
        
        if (htDictionary.count() != 0 && !checkStr.equals("\t")) {
            if (pick == 1) {
                input = 1;
                SpellChecker.taWrite.setText(checkStr);
            } else
                input = 2;
            while (readStr.hasNext()) {
                readStr.next();
                numWords++;
            }
            text    = new String[numWords];
            readStr = new Scanner(checkStr);
            while (readStr.hasNext()) {
                text[indexAdd] = readStr.next();
                indexAdd++;
            }
            errors = new String[text.length];

            while (indexCheck < text.length) {
                if (!htDictionary.contains(text[indexCheck].toLowerCase())) {
                    numErrors++;
                    errors[indexError] = text[indexCheck];
                    indexError++;
                }
                indexCheck++;
            }        
            SpellChecker.vbTextUpdates.getChildren().remove(txtChecked);
            SpellChecker.vbTextUpdates.getChildren().remove(txtReplaced);
            
            txtChecked = new Text(spellingChecked);
            txtChecked.setId("updateStyle");
            SpellChecker.vbTextUpdates.getChildren().add(txtChecked);
            showErrors(numErrors, errors);      
        
        } else if (htDictionary.count() == 0) {
            txtLoaded = new Text(ldDictFrst);
            txtLoaded.setId("updateStyle");
            SpellChecker.vbTextUpdates.getChildren().add(txtLoaded);
        } else if (checkStr.equals("\t")) {
            txtLoaded = new Text(checkInput);
            txtLoaded.setId("updateStyle");
            SpellChecker.vbTextUpdates.getChildren().add(txtLoaded);
        }
    }
    
    public static void showErrors(int num, String[] errors) {
        String   show;
        String[] closest;
        
        show = message + ": " + num;
        closest = new String[num];
        
        if (num > 0) {
            show += "\n" + suggestions + "\n";
            for (int i = 0; i < num; i++) 
                closest[i] = htDictionary.getClosest(errors[i]);
            for (int i = 0; i < num; i++) 
                show += errors[i] + arrow + closest[i] + "\n";
        }
        if (input == 1)         showFileLayout();
        else if (input == 2)    showWriteLayout();
        
        SpellChecker.taErrors.clear();
        SpellChecker.taErrors.setText(show);
        replaceErrors(errors, closest);
    }
    
    public static void showFileLayout() {
        SpellChecker.vbTextFmt.getChildren().remove(SpellChecker.hbTextStyle);
        SpellChecker.hbErrWrite.getChildren().remove(SpellChecker.vbRepErr);
        SpellChecker.hbErrWrite.getChildren().add(SpellChecker.vbRepErr);
        SpellChecker.vbErrorsFile.getChildren().remove(SpellChecker.hbErrWrite);
        SpellChecker.vbErrorsFile.getChildren().add(SpellChecker.hbErrWrite);
        SpellChecker.vbTextFmt.getChildren().remove(SpellChecker.btnUpdFile);
        SpellChecker.vbTextFmt.getChildren().add(SpellChecker.btnUpdFile);
    }
    
    public static void showWriteLayout() {
        SpellChecker.hbErrWrite.getChildren().remove(SpellChecker.vbRepErr);
        SpellChecker.hbErrWrite.getChildren().add(SpellChecker.vbRepErr);
    }
    
    public static void resetLayout() {
        SpellChecker.vbErrorsFile.getChildren().remove(SpellChecker.vbRepErr);
        SpellChecker.hbErrWrite.getChildren().remove(SpellChecker.vbRepErr);
        SpellChecker.vbErrorsFile.getChildren().remove(SpellChecker.hbErrWrite);
        SpellChecker.vbTextFmt.getChildren().remove(SpellChecker.btnUpdFile);
    }
    
    public static void replaceErrors(String[] errors, String[] replacement) {
        String[] oldText;
        
        oldText = getWords(SpellChecker.taWrite.getText());
        newText = "";
        
        for (int i = 0; i < oldText.length; i++)
            for (int j = 0; j < errors.length; j++)
                if (oldText[i] != null && oldText[i].equals(errors[j]))
                    if (!replacement[j].equals(noSugFound)) 
                        oldText[i] = replacement[j];
        
        for (String nextWord : oldText) 
            if (nextWord != null)
                newText += nextWord + " ";
    }
    
    public static void showReplacements() {
        SpellChecker.vbTextUpdates.getChildren().remove(txtReplaced);
        if (!SpellChecker.taWrite.getText().equals(newText)) {
            SpellChecker.taWrite.setText(newText);
            txtReplaced = new Text(repl);
            SpellChecker.taErrors.clear();
        } else 
            txtReplaced = new Text(noRepl);
        txtReplaced.setId("updateStyle");
        SpellChecker.vbTextUpdates.getChildren().add(txtReplaced);
    }
    
    public static void updateFile(String fileName) throws FileNotFoundException {
        FileOutputStream out; 
        PrintStream      writeToFile;
        
        if (!fileName.endsWith(".txt"))
            fileName += ".txt";
        
        out         = new FileOutputStream(fileName);
        writeToFile = new PrintStream(out);
        writeToFile.println(SpellChecker.taWrite.getText());
        
        SpellChecker.vbTextUpdates.getChildren().remove(txtUpdated);
        txtUpdated = new Text(fileUpd);
        txtUpdated.setId("updateStyle");
        SpellChecker.vbTextUpdates.getChildren().add(txtUpdated);
    }
    
    public static String readDoc(String fileName) throws FileNotFoundException {
        File    checkDoc;
        Scanner fileIn;
        String  result, line;
        
        if (!fileName.endsWith(".txt")) 
            fileName += ".txt";
        
        checkDoc = new File(fileName);
        result = "";
        
        if (checkDoc.exists()) {
            fileIn = new Scanner(checkDoc);
            while(fileIn.hasNextLine()) {
                line = fileIn.nextLine();
                result += line + "\n";
            }
        } else
            throw new FileNotFoundException(noFileExists);
        return result;
    }
    
    public static String[] getWords(String str) {
        int      index;
        String[] words;
        Scanner  readStr; 
        
        index   = 0;
        words   = new String[getArraySize(str)];
        readStr = new Scanner(str);
        
        while (readStr.hasNext()) {
            words[index] = readStr.next();
            index++;
        }
        return words;
    }
    
    public static int getArraySize(String str) {
        int numStrings;
        Scanner readStr;
        
        numStrings = 0;
        readStr    = new Scanner(str);
        
        while (readStr.hasNext()) {
            readStr.next();
            numStrings++;
        }
        numStrings *= 3;
        
        while (!isPrime(numStrings))
            numStrings++;
        
        return numStrings;
    }
    
    public static boolean isPrime(int n) {
        if (n%2 == 0)   
            return false;
        else 
            for (int i = 3; i*i < n; i += 2) 
                if (n%i == 0)
                    return false;
            
        return true;
    }

}