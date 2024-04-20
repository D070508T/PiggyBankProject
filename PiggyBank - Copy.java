import java.util.Random;
import java.util.Scanner;

public class PiggyBank {
    //Instance variables
    private int cost;
    private int sellCost;
    private int volume;
    private int capacity;
    private double money;
    private int[] coins;
    private int amountOfCoins;
    private String name;
    private String tag;
    private boolean canGainInterest;
    private boolean canWithdraw;
    private boolean canInvest;

    //Class variables
    public static double[] coinValues = {0.05, 0.10, 0.25, 1, 2};
    public static double bankBalance;

    //Constructor for basic Piggy Banks
    public PiggyBank(String _tag) {
        if (!_tag.equals("INVALID")) {
            tag = _tag;
            name = tag + " Piggy Bank";
            money = 0;
            coins = new int[5];
            amountOfCoins = 0;

            if (tag.equals("Savings")) {
                cost = 50;
                volume = 8;
                canGainInterest = true;
                canWithdraw = false;
                canInvest = false;
            } else if (tag.equals("Spending")) {
                cost = 10;
                volume = 4;
                canGainInterest = false;
                canWithdraw = true;
                canInvest = false;
            } else if (tag.equals("Investment")) {
                cost = 30;
                volume = 6;
                canGainInterest = false;
                canWithdraw = false;
                canInvest = true;
            }

            coins = new int[5];

            sellCost = (int) (0.8 * cost);
            capacity = (int) (volume / 0.08);
        }
    }

    //Constructor for custom Piggy Banks
    public PiggyBank(String _name, int _volume, boolean _canGainInterest, boolean _canWithdraw, boolean _canInvest) {
        tag = "Custom";
        name = _name;
        volume = _volume;
        money = 0;
        coins = new int[5];
        amountOfCoins = 0;
        canGainInterest = _canGainInterest;
        canWithdraw = _canWithdraw;
        canInvest = _canInvest;
        capacity = (int) (volume / 0.08);
        cost = 10 * volume;
        if (canGainInterest) {cost += 10;}
        if (canWithdraw) {cost += 10;}
        if (canInvest) {cost += 10;}
        sellCost = (int)(0.8*cost);
    }

    public void setCost(int Cost) {
        cost = Cost;
        sellCost = (int)(0.8*cost);
    }

    public int getCost() {
        return cost;
    }

    public int getSellCost() {
        return sellCost;
    }

    public void setVolume(int Volume) {
        volume = Volume;
        capacity = (int) (volume / 0.08);
    }

    public int getVolume() {
        return volume;
    }


    public int getCapacity() {
        return capacity;
    }

    public double getMoney() {
        return money;
    }

    public int[] getCoins() {
        return coins;
    }


    public void setName(String Name) {
        name = Name;
    }


    public String getName() {
        return name;
    }


    public String getTag() {
        return tag;
    }


    public boolean canGainInterest() {
        return canGainInterest;
    }


    public boolean canWithdraw() {
        return canWithdraw;
    }


    public boolean canInvest() {
        return canInvest;
    }


    public int spaceLeft() {
        return capacity - amountOfCoins;
    }

    public void updateMoney() {
        double oldMoney = money;

        money = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < coins[i]; j++) {
                money += coinValues[i];
            }
        }
    }


    public void addCoins(int amount, int type, double bankBalance) {
        double value = 0;

        value = coinValues[type-1];

        if (bankBalance >= value) {
            coins[type - 1] += amount;
            amountOfCoins += amount;
        } else {
            System.out.println("You do not have enough money.");
        }

        updateMoney();
    }
//
//    public void withdraw(double amount) {
//        double currentAmount = 0;
//        int oldAmountOfCoins = amountOfCoins;
//        int[] newCoins = new int[5];
//        for (int i = 0; i < 5; i++) {
//            newCoins[i] = coins[i];
//        }
//
//        for (int i = 4; i >= 0; i--) {
//            for (int j = 0; currentAmount < amount && j < coins[i]; j++) {
//                if (coins[i] > 0) {
//                    if (currentAmount + coinValues[i] <= amount) {
//                        currentAmount += coinValues[i];
//                        coins[i]--;
//                        amountOfCoins--;
//                        if (currentAmount == amount) {
//                            j = coins[i];
//                            i = -1;
//                        }
//                    }
//                }
//            }
//        }
//
//        if (currentAmount != amount) {
//            coins = newCoins;
//            amountOfCoins = oldAmountOfCoins;
//        }
//
//        updateMoney();
//    }

    public void gainInterest(double percent) {
        double amountToGain = percent * money;

        coins[0] ++;

        for (int i = 4; i >= 0; i--) {
            while (amountToGain >= coinValues[i]) {
                coins[i]++;
                amountOfCoins++;
                amountToGain -= coinValues[i];
            }
        }

        double balance = 0;

        updateMoney();
    }

    public void invest(double bankBalance) {
        Random random = new Random();

        double amountOfMoney = 0.3*money;

        int randInt = random.nextInt(1, 3);

        if (randInt == 1) {
            changeCoinsByAmount(amountOfMoney, true, bankBalance);
        } else {
            changeCoinsByAmount(amountOfMoney, false, bankBalance);
        }
    }

    public void changeCoinsByAmount(double amount, boolean add, double bankBalance) {
        if ((add && bankBalance >= amount) || (!add && money >= amount)) {
            double currentAmount = 0;
            int oldAmountOfCoins = amountOfCoins;
            int[] newCoins = new int[5];
            for (int i = 0; i < 5; i++) {
                newCoins[i] = coins[i];
            }

            for (int i = 4; i >= 0; i--) {
                for (int j = 0; currentAmount < amount && j < coins[i]; j++) {
                    if (coins[i] > 0) {
                        if (currentAmount + coinValues[i] <= amount) {
                            currentAmount += coinValues[i];
                            if (add) {
                                coins[i]++;
                                amountOfCoins++;
                            } else {
                                coins[i]--;
                                amountOfCoins--;
                            }
                            if (currentAmount == amount) {
                                j = coins[i];
                                i = -1;
                            }
                        }
                    }
                }
            }

            if (currentAmount != amount) {
                if (add) {
                    System.out.print("Cannot add $" + amount + ". Would you like to add $" + currentAmount + " instead? (y/n): ");
                } else {
                    System.out.print("Cannot remove $" + amount + " from this bank. Would you like to remove $" + currentAmount + " instead? (y/n): ");
                }

                Scanner scanner = new Scanner(System.in);

                if (!scanner.nextLine().equalsIgnoreCase("y")) {
                    coins = newCoins;
                    amountOfCoins = oldAmountOfCoins;
                }
            }

            updateMoney();

        } else {
            System.out.println("You do not have enough money.");
        }
    }

    public void upgradeAbility(String ability) {
        if (ability.equals("Interest")) {
            canGainInterest = true;
        } else if (ability.equals("Withdraw")) {
            canWithdraw = true;
        } else if (ability.equals("Invest")) {
            canInvest = true;
        }
    }


    public String toString() {
        return tag + ", " + name + "\n" +
                cost + ", " + sellCost + "\n" +
                volume + ", " + capacity + "\n" +
                money + ", " + coins[0] + ", " + coins[1] + ", " + coins[2] + ", " + coins[3] + ", " + "\n" +
                canGainInterest + ", " + canWithdraw + ", " + canInvest + "\n";
    }

    public static PiggyBank getPiggyBank(String _name, PiggyBank[] piggyBanks) {
        for (PiggyBank piggyBank : piggyBanks) {
            if (piggyBank.getName().equals(_name)) {
                return piggyBank;
            }
        }
        return new PiggyBank("INVALID");
    }
}