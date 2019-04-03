package hashtable;

public class HashTableFr extends HashTable {
    
    public HashTableFr() {
        list        = new String[DEFCAP];
        numElements = 0;
    }
    
    public HashTableFr(int size) {
        list        = new String[size];
        numElements = 0;
    }
    
    @Override
    public String removePunc(String element) {
        String modified;
        modified = "";
        
        for (int i = 0; i < element.length(); i++) 
            if (isLetter(element.charAt(i)))
                modified += element.charAt(i);
        
        return modified;
    }
    
    @Override
    public char getPunc(String element) {
        for (int i = 0; i < element.length(); i++) 
            if (!isLetter(element.charAt(i)))
                return element.charAt(i);
        return ' ';
    }
    
    
    public boolean isLetter(char c) {
        switch (c) {
            case 'a':  case 'à':  case 'b':  case 'c':  case 'ç':  case 'd':  
            case 'e':  case 'é':  case 'è':  case 'f':  case 'g':  case 'h':  
            case 'i':  case 'j':  case 'k':  case 'l':  case 'm':  case 'n':  
            case 'o':  case 'p':  case 'q':  case 'r':  case 's':  case 't':  
            case 'u':  case 'ù':  case 'v':  case 'w':  case 'x':  case 'y':  
            case 'z':  return true; 
            default:   return false;
        }
    }
    
   public int getEquiv(char letter) {
        switch (letter) {
            case 'a':  case 'à':   return  1;  case 'b':  return  2;  
            case 'c':  case 'ç':   return  3;  case 'd':  return  4;  
            case 'e':  case 'é':   case 'è':  return  5;  case 'f':  return  6;
            case 'g':  return  7;  case 'h':  return  8;  case 'i':  return  9;
            case 'j':  return 10;  case 'k':  return 11;  case 'l':  return 12;
            case 'm':  return 13;  case 'n':  return 14;  case 'o':  return 15;
            case 'p':  return 16;  case 'q':  return 17;  case 'r':  return 18;
            case 's':  return 19;  case 't':  return 20;  case 'u':  case 'ù':
                return 21; case 'v':  return 22;  case 'w':  return 23;  
            case 'x':  return 24; case 'y':  return 25;  case 'z':  
                return 26;  
            default:   return -1;
        }
    }
}
