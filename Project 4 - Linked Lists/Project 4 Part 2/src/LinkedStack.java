public class LinkedStack {
    protected LSNode topStack;
    int numOfNodes;

    public LinkedStack() {
        topStack = null;
        numOfNodes = 0;
    }
    
    public boolean isEmpty() {
        return topStack == null;
    }
    
    public void push(int element) {
        LSNode newNode = new LSNode(element);
        newNode.setLink(topStack);
        topStack = newNode;
        numOfNodes++;
    }
    
    public void pop() {
        if (!isEmpty()) {
            topStack = topStack.getLink();
            numOfNodes--;
        } else {
            System.out.println("Error: Could not compute expression. "
                             + "Check input and try again.");
            System.exit(0);
        }
    }
    
    public int top() {
        if (!isEmpty())         return topStack.getValue();
        else {
            System.out.println("Error: Could not compute expression. "
                             + "Check input and try again.");
            System.exit(0);
        }
        return -1;
    }

    public void push(String element) {
        LSNode newNode = new LSNode(element);
        newNode.setLink(topStack);
        topStack = newNode;
        numOfNodes++;
    }
    
    public String topString() {
        if (!isEmpty())         return topStack.getInfo();
        else {
            System.out.println("Error: Could not compute expression. "
                             + "Check input and try again.");
            System.exit(0);
        }
        return null;
    }

}