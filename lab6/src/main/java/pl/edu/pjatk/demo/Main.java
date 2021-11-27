package pl.edu.pjatk.demo;

public class Main {
    public static void main(String[] args) throws Exception {
        Debt debt = new Debt(DebtType.DECREASING,150000, 0.05, 12, 12, 250);
        System.out.println(debt);

    }
}
