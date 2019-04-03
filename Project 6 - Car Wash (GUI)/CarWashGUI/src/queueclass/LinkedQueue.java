package queueclass;

public class LinkedQueue {
    protected LLNode front, rear;
    int count;
    
    public LinkedQueue() {
        front = null;
        rear  = null;
        count = 0;
    }
    
    public void enqueue(String element) {
        LLNode newNode = new LLNode(element);
        if (rear == null)       front = newNode;
        else                    rear.setLink(newNode);
        rear = newNode;
        count++;
    }
    
    public String dequeue() {
        String element;
        if (isEmpty())
            throw new QueueUnderflowException("Dequeue attempted on empty queue.");
        else {
            element = front.getInfo();
            front = front.getLink();
            if (front == null)
                rear = null;
            count--;
        }
        return element;
    }

    public boolean isEmpty() {
        return count == 0;
    }
    
}