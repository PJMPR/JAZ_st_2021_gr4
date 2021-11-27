package pl.edu.pjatk.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class debtTests {

    Debt debt1 = new Debt(DebtType.DECREASING,150000, 0.05, 12, 12, 250);
    Debt debt2 = new Debt(DebtType.FLAT,150000, 0.05, 12, 12, 250);

    public debtTests() throws Exception {
    }

    @Test
    public void debtTypeShouldBeDecreasing() {
        Assertions.assertEquals(debt1.getDebtType(),DebtType.DECREASING);
    }

    @Test
    public void installmentsCountShouldBeEqualToInstallmentAmount() {
        Assertions.assertEquals(debt1.getInstallments().length, debt1.getn());
    }

    @Test
    public void debtHeightShouldNotBeEqualToTotalPayoff() {
        Assertions.assertNotEquals(debt1.getN(), debt1.getTotalPayoff());
    }

    @Test
    public void InstallmentHeightShouldDifferBetweenDifferentTypes() {
        Assertions.assertNotEquals(debt1.getI(), debt2.getI());
    }
}
