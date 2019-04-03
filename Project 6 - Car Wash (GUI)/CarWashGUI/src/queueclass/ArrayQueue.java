package queueclass;

public class ArrayQueue<T> {
    protected T[] elements;
    protected int numOfElements;
    protected int front;
    protected int rear;
    
    public ArrayQueue(int capacity) {
        elements      = (T[]) new Object[capacity];
        rear          = capacity - 1;
        numOfElements = 0;
    }
    
    public void enqueue(T element) {
        if (isFull())
            throw new 
            QueueOverflowException("Enqueue attempted on full queue.");
        else {
            rear           = (rear + 1) % elements.length;
            elements[rear] = element;
            numOfElements++;
        }
    }

    public T dequeue() {
        T oldFront;
        if (isEmpty())
            throw new 
            QueueUnderflowException("Dequeue attempted on empty queue.");
        else {
            oldFront        = elements[front];
            elements[front] = null;
            front           = (front + 1) % elements.length;
            numOfElements--;
            return oldFront;
        }
    }

    public boolean isEmpty() {
        return (numOfElements == 0);
    }

    public boolean isFull() {
        return (numOfElements == elements.length);
    }

    public int size() {
        return numOfElements;
    }
}