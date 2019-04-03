public class LinkedList {
    protected LLNode log;
    int numOfNodes;
    
    public LinkedList() {
        numOfNodes = 0;
    }
    
    public void add(String element) {
        LLNode newNode = new LLNode(element);
        LLNode currLog;
        currLog = log;
        
        if (currLog != null) {
            while(currLog.getLink() != null) {
                currLog = currLog.getLink();
            }
            currLog.setLink(newNode);
        } else {
            log = newNode;
        }
        numOfNodes++;  
    }
    
    public String get(int index) {
        LLNode node;
        
        if (log != null) {
            node = log;
            for (int i = 0; i < index; i++) {
                if (node.getLink() == null)     return null;
                node = node.getLink();
            }
            return node.getInfo();
        }
        return null;
    }
    
    public String remove() {
        LLNode node;
        node = log;
        
        if (log == null)    return  null;
        log = log.getLink();
        node.setLink(null);
        numOfNodes--;
        return node.getInfo();
    }
    
    public String remove(int index) {
        if (index == 0)     return remove();
        numOfNodes--;
        String name;
        LLNode temp, prev;
        
        name = get(index);
        temp = log;
        prev = null;
        
        if (temp != null && temp.getInfo().equals(name)) {
            log = temp.getLink();
            return name;
        }
        
        while (temp != null && !temp.getInfo().equals(name)) {
            prev = temp;
            temp = temp.getLink();
        }
        
        if (temp == null)       return name;
        prev.setLink(temp.getLink());
        return name;        
    }
    
    public int indexOf(String find) {
        LLNode node;
        String curr;
        int index;
        
        node = log;
        index = 0;
        
        while(node != null) {
            curr = node.getInfo();
            if (curr.equals(find)) {
                return index;
            }
            index++;
            node = node.getLink();
        }
        if ((index == getSize()) && (find == null))         return -1;
        else                                                return index;
    }
    
    public boolean isEmpty() {
        return getSize() == 0;
    }
        
    public int getSize() {
        return numOfNodes;
    }
    
    public boolean contains(String element) {
        LLNode node;
        node = log;
        
        while(node != null) {
            if (element.equals(node.getInfo()))     return true;
            else                                    node = node.getLink();
        }
        return false;
    }
    
    public void clear() {
        log = null;
    }
}