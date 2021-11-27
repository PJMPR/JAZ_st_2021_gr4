package pl.edu.pjatk.demo;

import static pl.edu.pjatk.demo.Functions.round;

public class Installment {
    private final double interestPart;
    private final double capitalPart;
    private final double total;
    private final int index;
    private final double fixedFee;

    public Installment(DebtType debtType, int index, double installmentHeight, double remainingDebt, double interestRate, double fixedFee) throws Exception {
        this.index = index;
        this.fixedFee = fixedFee;
        if (debtType == DebtType.FLAT) {
            this.interestPart = remainingDebt * interestRate * 1 / 12;
            this.total = installmentHeight + fixedFee;
            this.capitalPart = total - interestPart;
        } else if (debtType == DebtType.DECREASING){
            this.capitalPart = installmentHeight;
            this.interestPart = remainingDebt * interestRate * 1 / 12;
            this.total = capitalPart + interestPart + fixedFee;
        } else {
            throw new Exception("Debt type either unspecified or invalid");
        }

    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Installment " + index + " {" +
                "\n\tinterestPart=" + round(interestPart, 2) +
                "\n\tcapitalPart=" + round(capitalPart, 2) +
                "\n\tfixedFee=" + round(fixedFee, 2) +
                "\n\ttotal=" + round(total, 2) +
                "\n}";
    }

}
