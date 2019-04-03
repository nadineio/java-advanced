import java.io.File;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class FileSorting {
    // Written by: Nadine Mansour
    // Sorts three files that have 1-50000 in order, reverse, and randomly
    
    static final int    SIZE     = 50000;
    static       int[]  intArray = new int[SIZE];
    static       int    numOfSwaps, comparisons;    
    
    public static void main(String[] args) throws Exception {
        long startTime, endTime, totalRuntime;
        PrintToFile.fillAndPrintToFile();
        
        numOfSwaps = 0;
        comparisons = 0;
        readFile("In Order.txt"); 
        printArray("Ordered Array");
        
        startTime = System.nanoTime();
        insertionSort();
        endTime = System.nanoTime();
        totalRuntime = TimeUnit.MILLISECONDS.convert((endTime - startTime), 
                       TimeUnit.NANOSECONDS);
        System.out.println("Sorting Ordered Array Runtime: "
                          + totalRuntime + " milliseconds");
        System.out.println("Number of Swaps: " + numOfSwaps+ 
                           "\nNumber of Comparisons: " + comparisons);
        System.out.println();
        
        numOfSwaps = 0;
        comparisons = 0;
        readFile("Reverse Order.txt"); 
        printArray("Reverse Array");
        
        startTime = System.nanoTime(); 
        insertionSort();
        endTime = System.nanoTime();
        totalRuntime = TimeUnit.MILLISECONDS.convert((endTime - startTime), 
                       TimeUnit.NANOSECONDS);
        System.out.println("Sorting Reversed Array Runtime: "
                          + totalRuntime + " milliseconds");
        System.out.println("Number of Swaps: " + numOfSwaps + 
                           "\nNumber of Comparisons: " + comparisons);        
        System.out.println();
        
        numOfSwaps = 0;
        comparisons = 0;
        readFile("Random.txt"); 
        printArray("Random Array");
        
        startTime = System.nanoTime();
        insertionSort();
        endTime = System.nanoTime();
        totalRuntime = TimeUnit.MILLISECONDS.convert((endTime - startTime), 
                       TimeUnit.NANOSECONDS);
        System.out.println("Sorting Random Array Runtime: "
                          + totalRuntime + " milliseconds");
        System.out.println("Number of Swaps: " + numOfSwaps + 
                           "\nNumber of Comparisons: " + comparisons);
    }
    
    public static void readFile(String fileName) throws Exception {
        File file = new File(fileName);
        Scanner fileIn = new Scanner(file);
        int i;
        i = 0;
        while (fileIn.hasNext()) {
            intArray[i] = Integer.parseInt(fileIn.next());
            i++;
        }
    }
    
    public static void printArray(String arrayType) {
        DecimalFormat fmt = new DecimalFormat("00000");
        int value;
        
        System.out.println(arrayType + ": ");
        for (int i = 0; i < 30; i++) {
            value = intArray[i];
            if (((i+1) % 10) == 0) System.out.println(fmt.format(value));
            else                   System.out.print(fmt.format(value) + " ");
        }
    }

// Insertion Sort
    public static void insertionSort() {
        for (int count = 1; count < SIZE; count++) {
            insertItem(0, count);
            comparisons++;
        }
    }

    public static void insertItem(int startIndex, int endIndex) {
        boolean finished, moreToSearch;
        finished     = false;
        moreToSearch = true;

        while (moreToSearch && !finished) {
            if (intArray[endIndex] < intArray[endIndex - 1]) {
                swap(endIndex, endIndex - 1);
                numOfSwaps++;
                endIndex--;
                moreToSearch = (endIndex != startIndex);
            } else
                finished = true;
        }
    }

    public static void swap(int index1, int index2) {
        int tempValue;
        tempValue        = intArray[index1];
        intArray[index1] = intArray[index2];
        intArray[index2] = tempValue;
    }
}