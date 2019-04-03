public class LinkedStringLog {
    protected LLStringNode log;
    protected String name;

    public LinkedStringLog(String name) {
        this.name = name;
        log = null;
    }
    
    public void add(String element) {
        LLStringNode newNode = new LLStringNode(element);
        newNode.setLink(log);
        log = newNode;
    }
    
    public void remove(String element) {
        
    }
    
    public boolean isFull() {
        return false;
    }
    
    public int size() {
        int count;
        LLStringNode node;
        
        count = 0;
        node = log;
        
        while (node != null) {
            count++;
            node = node.getLink();
        }
        return count;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean contains(String element) {
        LLStringNode node;
        node = log;
        
        while (node != null) {
            if (element.equalsIgnoreCase(node.getInfo()))
                return true;
            else 
                node = node.getLink();
        }
        return false;
    }
    
    public void clear() {
        log = null;
    }
    
    public String printList() {
        String logString;
        LLStringNode node;
        int count; 
        
        logString = "\n" + name + "\n";
        node = log;
        count = 0;
        
        while (node != null) {
            count++;
            logString = logString + count + "." + node.getInfo() + "\n";
            node = node.getLink();
        }
        System.out.println(logString);
        return logString;
    }
}
