import java.io.File;
import java.util.Scanner;
import javafx.stage.Stage;

public class Match {
    static String info;
    static int num;
    static LinkedList men,   freeMen,     menPref, husband;
    static LinkedList women, freeWomen, womenPref, wife;
        
    static void readFile(Stage pStage, String fileName) throws Exception {
        men     = new LinkedList();   women     = new LinkedList();
        freeMen = new LinkedList();   freeWomen = new LinkedList();
        menPref = new LinkedList();   womenPref = new LinkedList();
        
        String temp, nextName;
        
        if (!fileName.endsWith(".txt")) fileName += ".txt";
        File file = new File(fileName);
        
        if (file.exists()) {
            Scanner fileIn = new Scanner(file);
            num = fileIn.nextInt();
            temp = fileIn.nextLine();
            info = "Number of men & women: " + num 
                 + "\nList of Men: " + fileIn.nextLine() 
                 + "\nList of Women: " + fileIn.nextLine() + "\n";
            
            for (int i = 0; i < num; i++) {
                nextName = fileIn.next().replace(':', ' ').trim();
                men.add(nextName);
                freeMen.add(nextName);
                for (int j = 0; j < num; j++) {
                    menPref.add(fileIn.next());
                }
            }
            for (int i = 0; i < num; i++) {
                nextName = fileIn.next().replace(':', ' ').trim();
                women.add(nextName);
                freeWomen.add(nextName);
                for (int j = 0; j < num; j++) {
                    womenPref.add(fileIn.next());
                }
            }
            StableMatchingGUI.match(pStage);
            StableMatchingGUI.listPreferences(pStage);
            
        } else {
            AlertBox.display("File Not Found", 
                    "The specified file does not exist.\n"
                  + "Please try again.");
            StableMatchingGUI.fileField.selectAll();
        }
        
    }
    
    static void menPropose(Stage primaryStage) {
        LinkedList currentManPref;
        LinkedList matchedWomanPref;
        String currentMan, herFiance, matchedWoman;
                
        husband = new LinkedList();
        wife    = new LinkedList();

        while (!freeMen.isEmpty()) {
            currentMan     = freeMen.remove();
            currentManPref = new LinkedList();

            for (int i = 0; i < num; i++)
                currentManPref.add(menPref.get
                                  (men.indexOf(currentMan)* num + i));
            matchedWoman = currentManPref.remove();
            
            while (!currentManPref.isEmpty()) {
                if (freeWomen.contains(matchedWoman)) {
                    freeWomen.remove(freeWomen.indexOf(matchedWoman));
                    wife.add(matchedWoman);
                    husband.add(currentMan);
                    break;
                } else {
                    herFiance = husband.get(wife.indexOf(matchedWoman));
                    matchedWomanPref = new LinkedList();
                    for (int i = 0; i < num; i++)
                        matchedWomanPref.add(womenPref.get
                                  (women.indexOf(matchedWoman) * num + i));
                    
                    if (matchedWomanPref.indexOf(herFiance)
                                > matchedWomanPref.indexOf(currentMan)) {
                        wife.remove(wife.indexOf(matchedWoman));
                        husband.remove(husband.indexOf(herFiance));
                        freeWomen.add(matchedWoman);
                        freeMen.add(herFiance);

                    } else  matchedWoman = currentManPref.remove();
                }
            }
        }
        
        StableMatchingGUI.showCouples(primaryStage);
    }
    
    static void womenPropose(Stage primaryStage) {
        LinkedList currentWomanPref;
        LinkedList matchedManPref;
        String currentWoman, hisFiance, matchedMan;
        
        wife    = new LinkedList();
        husband = new LinkedList();

        while (!freeWomen.isEmpty()) {
            currentWoman     = freeWomen.remove();
            currentWomanPref = new LinkedList();

            for (int i = 0; i < num; i++)
                currentWomanPref.add(womenPref.get
                                    (women.indexOf(currentWoman) * num + i));
            matchedMan = currentWomanPref.remove();
            
            while (!currentWomanPref.isEmpty()) {
                if (freeMen.contains(matchedMan)) {
                    freeMen.remove(freeMen.indexOf(matchedMan));
                    husband.add(matchedMan);
                    wife.add(currentWoman);
                    break;
                } else {
                    hisFiance = wife.get(husband.indexOf(matchedMan));
                    matchedManPref = new LinkedList();
                    for (int i = 0; i < num; i++)
                        matchedManPref.add(menPref.get
                              (men.indexOf(matchedMan) * num + i));
                    
                    if (matchedManPref.indexOf(hisFiance) 
                                    > matchedManPref.indexOf(currentWoman)) {
                        husband.remove(husband.indexOf(matchedMan));
                        wife.remove(wife.indexOf(hisFiance));
                        freeMen.add(matchedMan);
                        freeWomen.add(hisFiance);

                    } else      matchedMan = currentWomanPref.remove();
                }
            }
        }
        StableMatchingGUI.showCouples(primaryStage);
    }
    
}