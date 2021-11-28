package com.lab06.demo;

import com.lab06.demo.creditcalculators.CreditConstant;
import com.lab06.demo.creditcalculators.CreditDecreasing;
import com.lab06.demo.models.Calculation;
import com.lab06.demo.models.InstallmentType;
import com.lab06.demo.models.Timetable;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest
public class CalculationTest {

    private final int amount = 234431;
    private final int installmentCount = 322;
    private final InstallmentType installmentType = InstallmentType.CONSTANT;
    private final double percentage = 0.032;
    private final int fixedRate = 10;
    private final Calculation calculation = new Calculation(amount, installmentCount, installmentType, percentage, fixedRate);


    @Test
    public void creditConstantShouldReturnProperValuesInList() {


        List<Timetable> actual = new CreditConstant(calculation).constantRateCalculation();

        assertThat(actual.size(), is(322));
        assertThat(actual.get(299).getInterest(), is(338.17));
        assertThat(actual.get(299).getAmount(), is(1096.17));
    }

    @Test
    public void creditDecreasingShouldReturnProperValuesInList() {

        List<Timetable> actual = new CreditDecreasing(calculation).decreasingRateCalculation();

        assertThat(actual.size(), is(322));
        assertThat(actual.get(0).getInterest(), is(605.15));
        assertThat(actual.get(299).getInterest(), is(24.69));
        assertThat(actual.get(0).getAmount(), is(635.15));
        assertThat(actual.get(299).getAmount(), is(54.69));
    }
}