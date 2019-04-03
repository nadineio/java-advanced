package hashtable;

public class HashTable {
    public static final int      DEFCAP = 1000;
    public static       int      numElements;
    public static       String[] list;

    public HashTable() {
        list        = new String[DEFCAP];
        numElements = 0;
    }
    
    public HashTable(int size) {
        list        = new String[size];
        numElements = 0;
    }
    
    public boolean contains(String element) {
        for (int i = 0; i < list.length; i++)
            if (list[i] != null)
                if (list[i].equalsIgnoreCase(removePunc(element)))
                    return true;
        
        return false;
    }
    
    public String removePunc(String element) {
        String modified;
        modified = "";
        
        for (int i = 0; i < element.length(); i++) 
            if (Character.isLetter(element.charAt(i)))
                modified += element.charAt(i);
        
        return modified;
    }
    
    public char getPunc(String element) {
        for (int i = 0; i < element.length(); i++) 
            if (!Character.isLetter(element.charAt(i)))
                return element.charAt(i);
        return ' ';
    }
    
    public String getClosest(String element) {
        boolean[] capital;
        String    plain, closest, finWord;
        
        closest = "No replacement found";
        capital = new boolean[element.length()];        
        for (int i = 0; i < capital.length; i++)
            capital[i] = Character.isUpperCase(element.charAt(i));
        
        plain = removePunc(element.toLowerCase());
        for (int i = 0; i < list.length; i++)
            if (list[i] != null)
                if (list[i].contains(plain) || plain.contains(list[i]))
                    closest = list[i];
        
        finWord = "";
        if (closest.length() > capital.length)  
            capital = enlarge(capital, closest.length());
        
        if (!closest.equals("No replacement found")) {
            for (int i = 0; i < closest.length(); i++)
                if (capital[i])
                    finWord += Character.toUpperCase(closest.charAt(i));
                else
                    finWord += closest.charAt(i);
            if (!element.equals(plain))
                finWord += getPunc(element);
        } else
            finWord = closest;
        
        return finWord;
    }
    
    public boolean[] enlarge(boolean[] old, int newLength) {
        boolean[] larger;
        larger = new boolean[newLength];
        
        for (int i = 0; i < old.length; i++)
            larger[i] = old[i];
        
        return larger;
    }
    
    public String get(String element) {
        int location;
        location = getHashValue(element) % list.length;
        
        while (!list[location].equals(element))
            location = (location + 1) % list.length;
        
        return list[location];
    }
    
    public void insert(String element) {
        int location;
        location = getHashValue(element) % list.length;
        
        while (list[location] != null)
            location = (location + 1) % list.length;
        list[location] = element;
        numElements++;
    }
    
    public void remove(String element) {
        int location;
        location = getHashValue(element) % list.length;
        
        while (!list[location].equals(element))
            location = (location + 1) % list.length;
        
        list[location] = null;
        numElements--;
    }
    
    public int count() {
        return numElements;
    }
    
    public int getHashValue(String element) {
        int hashValue, power;
        
        hashValue = 0;
        power     = 3;
        element   = element.toLowerCase();
        
        while(!element.equals("")) {
            hashValue += getEquiv(element.charAt(0)) * Math.pow(31, power);
            element    = element.substring(1);
            if (power == 0)      power = 3;
            else                 power--;
        }
        return hashValue;
    }
    
    public int getEquiv(char letter) {
        switch (letter) {
            case 'a':  return  1;  case 'b':  return  2;  case 'c':  return  3;
            case 'd':  return  4;  case 'e':  return  5;  case 'f':  return  6;
            case 'g':  return  7;  case 'h':  return  8;  case 'i':  return  9;
            case 'j':  return 10;  case 'k':  return 11;  case 'l':  return 12;
            case 'm':  return 13;  case 'n':  return 14;  case 'o':  return 15;
            case 'p':  return 16;  case 'q':  return 17;  case 'r':  return 18;
            case 's':  return 19;  case 't':  return 20;  case 'u':  return 21;
            case 'v':  return 22;  case 'w':  return 23;  case 'x':  return 24;
            case 'y':  return 25;  case 'z':  return 26;  default:   return -1;
        }
    }
}
