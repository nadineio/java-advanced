public class Bag {
    public int counter = 0;
    public int size;
    public int[] bagOfInts;
    
    public Bag(int arraySize) {
        size      = arraySize;
        bagOfInts = new int[size];
    }

    public int getSize() {
        return size;
    }

    public int getCounter() {
        return counter;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
    
    public void addNumber(int num) {
        if (counter != size) {
        bagOfInts[counter] = num;
        counter++;
        }
    }
}