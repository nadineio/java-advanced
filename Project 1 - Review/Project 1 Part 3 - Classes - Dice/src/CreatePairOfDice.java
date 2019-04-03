public class CreatePairOfDice {
    // Written by: Nadine Mansour
    // Prints a pair of dice

    public static void main(String[] args) {
        Die die1 = new Die();
        Die die2 = new Die();
        int valueOfDie1, valueOfDie2, sum;

        valueOfDie1 = (int) ((Math.random() * 6) + 1);
        valueOfDie2 = (int) ((Math.random() * 6) + 1);

        die1.setValue(valueOfDie1);
        die1.getValue();

        die2.setValue(valueOfDie2);
        die2.getValue();
        
        sum = valueOfDie1 + valueOfDie2;
        System.out.println("Sum: " + sum);
    }
}

class Die {
    int value;
    Die()      {    value = 0;   }

    public void setValue(int n) {    value = n;   }

    public void getValue() {
        switch (value) {
            case 1: System.out.println("\n   *\n\n");                 break;
            case 2: System.out.println("*\n\n      *\n");             break;
            case 3: System.out.println("*\n   *\n      *\n");         break;
            case 4: System.out.println("*     *\n\n*     *\n");       break;
            case 5: System.out.println("*     *\n   *\n*     *\n");   break;
            case 6: System.out.println("*  *  *\n\n*  *  *\n");       break;
            default:                                                  break;
        }
    }
}