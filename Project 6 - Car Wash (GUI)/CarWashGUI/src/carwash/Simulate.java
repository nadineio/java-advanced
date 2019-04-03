package carwash;

import java.util.Random;
import javafx.stage.Stage;
import queueclass.ArrayQueue;

public class Simulate {
    static final int  MAX_CARS    = 8;
    static ArrayQueue custArrival = new ArrayQueue(MAX_CARS + 1);
    static Customer[] customer;
    static int        index, carsBypassed;
    static String     oldMsg,   durationUnits;
    
    public static void runShift(Stage primaryStage) {
        Random         randArrival  = new Random();
        Customer       nextCar;
        boolean[]      minute;
        int            duration, carsWaiting, randArrivalMin;
        
        carsWaiting    = Integer.parseInt(CarWashGUI.tfCarsWaiting.getText());
        duration       = CarWashGUI.getDuration();
        oldMsg         = "";
        randArrivalMin = 0;
        
        CarWashGUI.setPermanentText(carsWaiting);
        
        customer = new Customer[duration];
        minute   = new boolean[duration];
        while(randArrivalMin < duration) { 
            //Randomly fills array with true to indicate customer arrival
            minute[randArrivalMin] = true;
            randArrivalMin += randArrival.nextInt(5) + 1;
        }
        
        printUpdate(duration + "-minute shift started.");
        printUpdate("\t" + carsWaiting + " car(s) waiting when"
                                       + "\n\tcar wash opened.");  
        if (carsWaiting > MAX_CARS) {
            carsBypassed = carsWaiting - MAX_CARS;
            carsWaiting  = MAX_CARS;
            printUpdate("\t" + carsBypassed + " car(s) bypassed.");
        }
        
        for (index = 0; index < carsWaiting; index++) {
            printUpdate("Car " + (index + 1) + " waiting at open.");
            nextCar = new Customer(0);
            enqueueCar(nextCar);
        }
        
        for (int minNow = 0; minNow < duration; minNow++) {
            if (minute[minNow]) {
                printUpdate("Minute " + (minNow + 1) + ": Car arrived.");
                nextCar = new Customer(minNow);
                if (!custArrival.isFull())
                    enqueueCar(nextCar);
                else {
                    carsBypassed++;
                    showBypassed(custArrival.size() - 1);
                    customer[index] = null;
                    index--;
                } 
                index++;
            }
            dequeueDone(minNow);
        } 
        endShift(duration, primaryStage);
    }
    
    public static void enqueueCar(Customer newCar) {
        Random randService = new Random();
        int    wait;
        
        if (index == 0)
            newCar.setWait(newCar.arrival);
        else {
            wait = customer[index - 1].getDone() - newCar.arrival;
            if (wait >= 0)  newCar.setWait(wait);
            else            newCar.setWait(0);
        }
        newCar.setService(randService.nextInt(4) + 2);
        newCar.setDone();
        custArrival.enqueue(newCar);
        customer[index] = newCar;
        showInfo(newCar.wait, newCar.service);
    }
    
    public static void dequeueDone(int min) {
        for (int i = index - 1; i >= 0 ; i--)
            if (customer[i].getDone() == min)
                custArrival.dequeue();
    }
    
    public static void endShift(int duration, Stage primaryStage) {
        printUpdate(duration + "-minute shift ended.");
        CarWashGUI.btnGo.setText("Get Report");
        CarWashGUI.btnGo.setOnAction(e -> getResults(primaryStage));
    }
    
    public static void getResults(Stage primaryStage) {
        String message;
        message = "Total customers served:\n"                        + index;
        message += "\n\nMinutes car wash was idle:\n" + getIdle() + " mins.";
        message += "\n\nAverage wait time:\n" +      getAvgWait() + " mins.";
        message += "\n\nLongest wait time:\n" +  getLongestWait() + " mins.";
        message += "\n\nCars that bypassed car wash:\n"       + carsBypassed;
    
        CarWashGUI.taReport.setText(message);
        CarWashGUI.btnGo.setText("Close");
        CarWashGUI.btnGo.setOnAction(e -> CarWashGUI.close(primaryStage));
    }
    
    public static int getIdle() {
        int idleT;
        idleT = 0;
        
        for (int i = 1; i < index; i++) 
            if (customer[i].getArrival() > customer[i-1].getDone())
                idleT += customer[i].getArrival() - customer[i-1].getDone();
            else if (customer[i].getArrival() == customer[i-1].getDone()) 
                idleT++;
        
        return idleT;
    }
    
    public static int getAvgWait() {
        int total;
        total = 0;
        
        for (int i = 0; i < index; i++)
            total += customer[i].wait;
        
        return total / index;
    }
    
    public static void sortWait() {
        Customer exchange;
        boolean  flag;
        flag = true;
        
        while (flag) {
            flag = false;
            for (int i = 0; i < index; i++) {
                if (customer[i+1] != null && 
                    customer[i].wait > customer[i+1].wait) {
                    exchange = customer[i];
                    customer[i] = customer[i+1];
                    customer[i+1] = exchange;
                    flag = true;
                }
            }
        }  
    }
    
    public static int getLongestWait() {
        sortWait();
        return customer[index - 1].wait;
    }
    
    public static void printUpdate(String message) {
        message += "\n";
        oldMsg += message;
        CarWashGUI.taUpdate.setText(oldMsg);
    }
    
    public static void showInfo(int wait, int service) {
        String message;
        
        if (wait == 1)  message = "\tWait time: " + wait + " min.";
        else            message = "\tWait time: " + wait + " mins.";
        
        message += "\n\tService time: " + service + " mins.";
        printUpdate(message);
    }
    
    public static void showBypassed(int numOfCars) {
        printUpdate("\t" +numOfCars+ " cars waiting.\n\tCustomer bypassed.");
    }
}
