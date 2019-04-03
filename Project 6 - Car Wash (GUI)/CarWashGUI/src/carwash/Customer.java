package carwash;

public class Customer {
    protected int arrival;
    protected int wait;
    protected int service;
    protected int done;

    public Customer(int arrival) {
        this.arrival = arrival;
    }
    
    public int getArrival() {
        return arrival;
    }

    public int getWait() {
        return wait;
    }
    
    public int getService() {
        return service;
    }

    public int getDone() {
        return done;
    }
    
    public void setArrival(int arrival) {
        this.arrival = arrival;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public void setService(int service) {
        this.service = service;
    }
    
    public void setDone() {
        this.done = arrival + wait + service;
    }
}