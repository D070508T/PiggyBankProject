import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        double bankBalance = 200;
        PiggyBank[] banks = new PiggyBank[3];

        banks[0] = new PiggyBank("Savings");
        banks[1] = new PiggyBank("Investment");
        banks[2] = new PiggyBank("Spending");
        if (PiggyBank.getPiggyBank("Savings Piggy Bank", banks).getTag().equals("INVALID")) {
            banks[2] = new PiggyBank("Savings");
        } else {
            banks[2] = new PiggyBank("Sample", 300, true, true, true);
        }

        System.out.println(Arrays.toString(banks));
    }
}