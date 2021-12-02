package com.example.demo;

import com.example.demo.credit.CreditConstant;
import com.example.demo.credit.CreditDecreasing;
import com.example.demo.models.Calculation;
import com.example.demo.models.InstallmentType;
import com.example.demo.models.Timetable;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest
public class DemoApplicationTest {

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

		assertThat(actual.size(), is(300));
		assertThat(actual.get(0).getInterest(), is(1175.0));
		assertThat(actual.get(299).getInterest(), is(3.92));
		assertThat(actual.get(0).getAmount(), is(1205.0));
		assertThat(actual.get(299).getAmount(), is(33.92));
	}
}