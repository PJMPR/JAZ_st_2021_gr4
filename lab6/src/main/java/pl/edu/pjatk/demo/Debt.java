package pl.edu.pjatk.demo;

import java.util.Arrays;

import static pl.edu.pjatk.demo.Functions.round;

public class Debt {
    private final double N;                           //height
    private final double r;                           //annualInterestRate
    private final double k;                           //annualInstallments
    private final int n;                              //installmentAmount
    private final double I;                           //installment height
    private final Installment[] installments;         //installments table
    private final double totalPayoff;
    private final DebtType debtType;

    public Debt(DebtType debtType ,double Height, double annualInterestRate, double annualInstallments, int installmentAmount, double fixedFee) throws Exception {
        this.N = Height;
        this.r = annualInterestRate;
        this.k = annualInstallments;
        this.n = installmentAmount;

        this.installments = new Installment[n];

        this.debtType = debtType;

        double payoff = 0;
        switch (debtType) {
            case FLAT:
                this.I = (N * r) / (k * (1 - Math.pow(k / (k + r), n)));
                for (int i = 1; i <= n; i++) {
                    installments[i - 1] = new Installment(DebtType.FLAT, i, I, N - (i - 1) * I, annualInterestRate, fixedFee);
                    payoff += installments[i - 1].getTotal();
                }
                break;
            case DECREASING:
                this.I = N / n;
                for (int i = 1; i <= n; i++) {
                    installments[i - 1] = new Installment(DebtType.DECREASING, i, I, N - (i - 1) * I, annualInterestRate, fixedFee);
                    payoff += installments[i - 1].getTotal();
                }
                break;
            default:
                throw new Exception("Debt type either unspecified or invalid");
        }
        this.totalPayoff = payoff;

    }

    public double getN() {
        return N;
    }

    public double getn() {
        return n;
    }

    public double getI() {
        return I;
    }

    public Installment[] getInstallments() {
        return installments;
    }

    public double getTotalPayoff() {
        return totalPayoff;
    }

    public DebtType getDebtType() {
        return debtType;
    }

    @Override
    public String toString() {
        return "Debt {" +
                "\n\tType=" + debtType +
                "\n\tHeight=" + N +
                "\n\tAnnual Interest Rate=" + r +
                "\n\tAnnual Installments=" + k +
                "\n\tInstallment Amount=" + n +
                "\n\tInstallment Height=" + round(I, 2) +
                "\n\tTotal payoff=" + round(totalPayoff, 2) +
                "\n}" +

                Arrays.toString(installments);
    }


}