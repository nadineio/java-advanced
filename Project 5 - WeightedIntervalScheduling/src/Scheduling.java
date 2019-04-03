import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Scheduling {
    // Written by: Eyad Elissa
    
    static Job[]      jobs;
    static int[]      memArr;
    static int        numOfJobs;
    static String     jobIndices = "";
    public static void main(String[] args) throws FileNotFoundException {
        String   fileName, format;
        File     file;
        Scanner  stdin, fileIn;
        Job nextJob;
        int      start, end, weight, optimal;
        
        System.out.print("Enter a file name for the input: ");
        stdin    = new Scanner(System.in);
        fileName = stdin.next();
        
        if (!fileName.endsWith(".txt"))    fileName += ".txt";
        
        file   = new File(fileName);
        if (file.exists()) {
            fileIn    = new Scanner(file);
            numOfJobs = fileIn.nextInt();
            jobs      = new Job[numOfJobs];

            for (int i = 0; i < numOfJobs; i++) {
                start  = fileIn.nextInt();
                end    = fileIn.nextInt();
                weight = fileIn.nextInt();

                nextJob = new Job(start, end, weight);
                jobs[i] = nextJob;
            }
            
            format = "\nJob Details {Start Time, End Time, Weight}";
            System.out.println("Number of Jobs: " + numOfJobs + format);
            for (int j = 0; j < numOfJobs; j++)
                System.out.println("\tJob " + (j + 1) + ": " + jobs[j]);

            computeLargestCompatible(); 
            memArr     = new int[numOfJobs];
            optimal    = memoizeOpt(numOfJobs - 1);
            findSol(numOfJobs - 1);
            
            System.out.println("Optimum Profit: " + optimal + 
                               "\nUsing Jobs: " + jobIndices.trim());
        } else
            System.out.println("Specified file does not exist.");
    }
    
    static void computeLargestCompatible() {
        //Sets largest index compatible with given job
        //If none exist, sets it equal to -1
        for (int i = numOfJobs - 1; i >= 0; i--) {
            for (int j = i - 1; j >= -1; j--) {
                if (j == -1)
                    jobs[i].setMostComp(j);
                else if (jobs[i].start >= jobs[j].endTime) {
                    jobs[i].setMostComp(j);
                    break;
                } 
            }
        }
    }
    
    static int memoizeOpt(int j) {
        if (j == -1)      
            return 0;
        else if (isEmpty(memArr[j]))
            memArr[j] = max(jobs[j].weight + memoizeOpt(jobs[j].mostComp), 
                                                       memoizeOpt(j - 1));
        return memArr[j];
    }
    
    static void findSol(int j) {
        jobIndices += " ";
        if  (j == 0)        
            jobIndices += (j + 1);
        else if (jobs[j].weight + memArr[jobs[j].mostComp] > memArr[j-1]) {
            jobIndices += (j + 1);
            findSol(jobs[j].mostComp);
        } else if (j >= 1)
            findSol(j-1);
    }

    static boolean isEmpty(int i) {
        return i == 0;
    }
    
    static int max(int num1, int num2) {
        if      (num1 > num2)   return num1;
        else if (num1 < num2)   return num2;
        else                    return -1;
    }
}