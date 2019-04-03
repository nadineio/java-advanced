import java.util.Scanner;

public class ReverseStrings {
    // Written By: Nadine Mansour
    // Reads in a series of words terminated by a sentinel 
    // and prints the words in reverse order of entry
    
    static final String SENTINEL = "DONE";
    public static void main(String[] args) {
        Scanner stdin     = new Scanner(System.in);
        LinkedStack stack = new LinkedStack();
        
        String nextWord;
        nextWord = "";
        
        System.out.print("Enter a series of words followed by \"" 
                                                +SENTINEL+ "\": ");
        while (!nextWord.equals(SENTINEL)) {
            stack.push(nextWord);
            nextWord = stdin.next();
        }   
        
        System.out.print("Reverse is: ");
        while (!stack.isEmpty()) {
            nextWord = stack.top();
            stack.pop();
            System.out.print(nextWord + " ");
        }
        System.out.println();
    }
    
}