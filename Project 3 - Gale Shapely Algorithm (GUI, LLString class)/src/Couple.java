public class Couple {
    
    public static Couple[] menMatches, womenMatches; 
    public String man, firstChoice, secondChoice, thirdChoice, fourthChoice, fifthChoice;

    public Couple(String man, String firstChoice, String secondChoice, String thirdChoice, String fourthChoice, String fifthChoice) {
        this.man = man;
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;
        this.thirdChoice = thirdChoice;
        this.fourthChoice = fourthChoice;
        this.fifthChoice = fifthChoice;
    }

    public String getMan() {
        return man;
    }
    
    public String getFirstChoice() {
        return firstChoice;
    }

    public String getSecondChoice() {
        return secondChoice;
    }

    public String getThirdChoice() {
        return thirdChoice;
    }

    public String getFourthChoice() {
        return fourthChoice;
    }

    public String getFifthChoice() {
        return fifthChoice;
    }

    public void setMan(String man) {
        this.man = man;
    }
    
    public void setFirstChoice(String firstChoice) {
        this.firstChoice = firstChoice;
    }

    public void setSecondChoice(String secondChoice) {
        this.secondChoice = secondChoice;
    }

    public void setThirdChoice(String thirdChoice) {
        this.thirdChoice = thirdChoice;
    }

    public void setFourthChoice(String fourthChoice) {
        this.fourthChoice = fourthChoice;
    }

    public void setFifthChoice(String fifthChoice) {
        this.fifthChoice = fifthChoice;
    }
}