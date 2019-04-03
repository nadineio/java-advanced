package hashtable;

public class HashTableAr extends HashTable {
    
    public HashTableAr() {
        list        = new String[DEFCAP];
        numElements = 0;
    }
    
    public HashTableAr(int size) {
        list        = new String[size];
        numElements = 0;
    }
    
    @Override
    public boolean contains(String element) {
        for (int i = 0; i < list.length; i++)
            if (list[i] != null)
                if (list[i].compareTo(removePunc(element)) == 0)
                    return true;
        
        return false;
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
    
    @Override
    public String getClosest(String element) {
        String plain, closest;
        
        closest = "No replacement found";
        plain = removePunc(element.toLowerCase());
        for (int i = 0; i < list.length; i++)
            if (list[i] != null)
                if (list[i].contains(plain) || plain.contains(list[i]))
                    closest = list[i];
        
        return closest;
    }
    
    @Override
    public String get(String element) {
        int location;
        location = getHashValue(element) % list.length;
        
        while (!list[location].equals(element))
            location = (location + 1) % list.length;
        
        return list[location];
    }
    
    @Override
    public void insert(String element) {
        int location;
        location = getHashValue(element) % list.length;
        
        while (list[location] != null)
            location = (location + 1) % list.length;
        list[location] = element;
        numElements++;
    }
    
    @Override
    public void remove(String element) {
        int location;
        location = getHashValue(element) % list.length;
        
        while (!list[location].equals(element))
            location = (location + 1) % list.length;
        
        list[location] = null;
        numElements--;
    }
    
    @Override
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
    
    public boolean isLetter(char c) {
        switch (c) {
            case 'ا':  case 'أ':  case 'إ':  case 'آ':  case 'ب':  case 'ت':  
            case 'ث':  case 'ج':  case 'ح':  case 'خ':  case 'د':  case 'ذ':  
            case 'ر':  case 'ز':  case 'س':  case 'ش':  case 'ص':  case 'ض':  
            case 'ط':  case 'ظ':  case 'ع':  case 'غ':  case 'ف':  case 'ق':  
            case 'ك':  case 'ل':  case 'م':  case 'ن':  case 'ه':  case 'و':  
            case 'ؤ':  case 'ي':  case 'ئ':  case 'ى':  case 'ء':  return true; 
            default:   return false;
        }
    }
   
    @Override
    public int getEquiv(char letter) {
        switch (letter) {
            case 'ا':  case 'أ':  case 'إ':  case 'آ':  return 1; 
            case 'ب':  return 2;  case 'ت':  return 3;  case 'ث':  return 4;  
            case 'ج':  return 5;  case 'ح':  return 6;  case 'خ':  return 7;  
            case 'د':  return 8;  case 'ذ':  return 9;  case 'ر':  return 10; 
            case 'ز':  return 11; case 'س':  return 12; case 'ش':  return 13; 
            case 'ص':  return 14; case 'ض':  return 15; case 'ط':  return 16; 
            case 'ظ':  return 17; case 'ع':  return 18; case 'غ':  return 19; 
            case 'ف':  return 20; case 'ق':  return 21; case 'ك':  return 22; 
            case 'ل':  return 23; case 'م':  return 24; case 'ن':  return 25; 
            case 'ه':  return 26; case 'و':  case 'ؤ':  return 27; 
            case 'ي':  case 'ئ':  case 'ى':  return 28; case 'ء':  return 29;                      
            default:   return -1;
        }
    }
}
