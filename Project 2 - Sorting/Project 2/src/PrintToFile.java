import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class PrintToFile {
    public static void fillAndPrintToFile() throws Exception {
        fillValuesInOrder();
        printToFile("In Order.txt");
        
        fillValuesInReverse();
        printToFile("Reverse Order.txt");
        
        fillValuesRandom();
        printToFile("Random.txt");
    }
    
    public static void fillValuesInOrder() {
        for (int i = 0; i < FileSorting.SIZE; i++)
            FileSorting.intArray[i] = i + 1;
    }

    public static void fillValuesInReverse() {
        int j = 0;
        for (int i = FileSorting.SIZE; j < FileSorting.SIZE; i--) {
            FileSorting.intArray[j] = i;
            j++;
        }
    }

    public static void fillValuesRandom() {
        Random rand = new Random();

        for (int i = 0; i < FileSorting.SIZE; i++)
            FileSorting.intArray[i] = rand.nextInt(50000) + 1;
    }
    
    public static void printToFile(String fileName) throws Exception {
        FileOutputStream out = new FileOutputStream(fileName);
        PrintStream      p   = new PrintStream(out);
        
        for (int i = 0; i < FileSorting.SIZE; i++) 
            p.println(FileSorting.intArray[i]);
    }
}