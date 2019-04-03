public class CodingGuidelines {
    // Written by: Nadine Mansour
    // Rewrites given code according to the coding guidelines

    public static void main(String[] args) {
        char c;
        c = getChoice();
        
        switch (c) {
            case 'a':   case 'A':   processOptionA();   break;
            case 'i':   case 'I':   processOptionI();   break;
            case 'w':   case 'W':   processOptionW();   break;
            default:                System.out.println("Not a valid choice\n");
        }
    }

    public static char getChoice() {
        char c;
        c = 'l';
        return c;
    }
    
    public static void processOptionA() {
        
    }
    
    public static void processOptionI() {
        
    }
    
    public static void processOptionW() {
        
    }
}
