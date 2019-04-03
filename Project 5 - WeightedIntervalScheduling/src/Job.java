public class Job {
    int start, endTime, weight, mostComp;

    public Job(int startTime, int endTime, int weight) {
        this.start = startTime;
        this.endTime   = endTime;
        this.weight    = weight;
    }

    public int getStartTime() {
        return start;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getWeight() {
        return weight;
    }
    
    public int getMostComp() {
        return mostComp;
    }

    public void setStartTime(int startTime) {
        this.start = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public void setMostComp(int mostComp) {
        this.mostComp = mostComp;
    }
    
    @Override
    public String toString() {
        String jobString;
        jobString = "{"  + start +
                    ", " +  endTime  + 
                    ", " +  weight   + "}";
        return jobString;
    }

}