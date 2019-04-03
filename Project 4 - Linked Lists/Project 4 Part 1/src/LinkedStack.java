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
    
    public void push(String element) {
        LSNode newNode = new LSNode(element);
        newNode.setLink(topStack);
        topStack = newNode;
        numOfNodes++;
    }
    
    public void pop() {
        if (!isEmpty()) {
            topStack = topStack.getLink();
            numOfNodes--;
        } else
            System.out.println("Stack is empty.");
    }
    
    public String top() {
        if (!isEmpty())         return topStack.getInfo();
        else                    System.out.println("Stack is empty.");
        return null;
    }

}