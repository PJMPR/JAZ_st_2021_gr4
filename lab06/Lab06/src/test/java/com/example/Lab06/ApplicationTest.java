package com.example.Lab06;

import controller.Calculator;
import controller.Installment;
import controller.InstallmentType;
import controller.Timetable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ApplicationTest {
	Calculator calculator = new Calculator();

	@Test
	void contextLoads() {
	}

	@Test
	public void timetableShouldHave3Installments(){
		Timetable timetable = new Timetable(10000,3, InstallmentType.CONSTANT,0.01,0);
		List<Installment> installments =  calculator.calculateInstalments(timetable);
		Assertions.assertEquals(3,installments.size());
	}

	@Test
	public void allValuesShouldEqual1InTimetable(){
		Timetable timetable = new Timetable(1,1,InstallmentType.DECREASING,1,1);
		Assertions.assertEquals(1,timetable.getAmount());
		Assertions.assertEquals(1,timetable.getInstallmentCount());
		Assertions.assertEquals(1,timetable.getFixedFee());
		Assertions.assertEquals(1,timetable.getPercentage());
	}

	@Test
	public void timetableShouldBeConstant(){
		Timetable timetable = new Timetable(1,1,InstallmentType.CONSTANT,1,1);
		Assertions.assertEquals(InstallmentType.CONSTANT,timetable.getInstallmentType());
	}

	@Test
	public void timetableShouldBeDescreasing(){
		Timetable timetable = new Timetable(1000,4,InstallmentType.DECREASING,0.01,0);
		List<Installment> installments =  calculator.calculateInstalments(timetable);
		Assertions.assertEquals(InstallmentType.DECREASING,timetable.getInstallmentType());
		Assertions.assertTrue(installments.get(0).getAmount() > installments.get(1).getAmount());
		Assertions.assertTrue(installments.get(1).getAmount() > installments.get(2).getAmount());
		Assertions.assertTrue(installments.get(2).getAmount() > installments.get(3).getAmount());
	}

}
