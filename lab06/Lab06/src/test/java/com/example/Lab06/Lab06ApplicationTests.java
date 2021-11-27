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
class Lab06ApplicationTests {
	Calculator calculator = new Calculator();

	@Test
	public void timetableShouldHave10Installments(){
		Timetable timetable = new Timetable(10000,10, InstallmentType.CONSTANT,0.01,0);
		List<Installment> installments =  calculator.calculateInstalments(timetable);
		Assertions.assertEquals(10,installments.size());
	}

	@Test
	public void timetableShouldHaveAllValuesEqualTo1(){
		Timetable timetable = new Timetable(1,1,InstallmentType.DECREASING,1,1);
		Assertions.assertEquals(1,timetable.getAmount());
		Assertions.assertEquals(1,timetable.getInstallmentCount());
		Assertions.assertEquals(1,timetable.getFixedFee());
		Assertions.assertEquals(1,timetable.getPercentage());
	}

	@Test
	public void timetableShouldHaveInstallmentTypeSetAsCONSTANT(){
		Timetable timetable = new Timetable(1,1,InstallmentType.CONSTANT,1,1);
		Assertions.assertEquals(InstallmentType.CONSTANT,timetable.getInstallmentType());
	}

	@Test
	public void timetableShouldHaveCONSTANTInstallments(){
		Timetable timetable = new Timetable(1000,4,InstallmentType.CONSTANT,0,0);
		List<Installment> installments =  calculator.calculateInstalments(timetable);
		Assertions.assertEquals(InstallmentType.CONSTANT,timetable.getInstallmentType());
		Assertions.assertEquals(250,installments.get(0).getAmount());
		Assertions.assertEquals(250,installments.get(1).getAmount());
		Assertions.assertEquals(250,installments.get(2).getAmount());
		Assertions.assertEquals(250,installments.get(3).getAmount());
	}

	@Test
	public void timetableShouldHaveDECREASINGInstallments(){
		Timetable timetable = new Timetable(1000,4,InstallmentType.DECREASING,0.01,0);
		List<Installment> installments =  calculator.calculateInstalments(timetable);
		Assertions.assertEquals(InstallmentType.DECREASING,timetable.getInstallmentType());
		Assertions.assertTrue(installments.get(0).getAmount() > installments.get(1).getAmount());
		Assertions.assertTrue(installments.get(1).getAmount() > installments.get(2).getAmount());
		Assertions.assertTrue(installments.get(2).getAmount() > installments.get(3).getAmount());
	}

}
