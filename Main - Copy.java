public class Main {
    public static void main(String[] args) {
        double bankBalance = 200;
        PiggyBank bank = new PiggyBank("Savings");
        System.out.println(bank.getMoney());

        System.out.println(bankBalance);
        bank.addCoins(10, 5, bankBalance);
        bank.addCoins(5, 2, bankBalance);
        System.out.println(bankBalance);
        System.out.println("Adding 10 toonies and 5 dimes ($20.50)\n\n");

        System.out.println(bankBalance);
        bank.gainInterest(0.02);
        System.out.println(bankBalance);
        System.out.println("Gaining interest 2%\n\n");

        System.out.println(bankBalance);
        bank.changeCoinsByAmount(10.40, false, bankBalance);
        System.out.println(bankBalance);
        System.out.println("Withdrawing $10.40\n\n");

        System.out.println(bankBalance);
        bank.gainInterest(0.1);
        System.out.println(bankBalance);
        System.out.println("Gaining interest 1%\n\n");
    }
}