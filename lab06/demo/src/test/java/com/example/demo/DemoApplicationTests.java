package com.example.demo;

import com.example.demo.controller.Calculator;
import com.example.demo.controller.Installment;
import com.example.demo.controller.InstallmentType;
import com.example.demo.controller.Timetable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {
	Calculator calculator = new Calculator();

	@Test
	public void timetable_Should_Have_10_Installments(){
		Timetable timetable = new Timetable(10000,10, InstallmentType.CONSTANT,0.01,0);
		List<Installment> installments =  calculator.calculateInstalments(timetable);
		Assertions.assertEquals(10,installments.size());
	}

	@Test
	public void timetable_Should_Have_Installment_Type_Set_As_CONSTANT(){
		Timetable timetable = new Timetable(1,1,InstallmentType.CONSTANT,1,1);
		Assertions.assertEquals(InstallmentType.CONSTANT,timetable.getInstallmentType());
	}

	@Test
	public void timetable_Should_Have_CONSTANT_Installments(){
		Timetable timetable = new Timetable(1000,4,InstallmentType.CONSTANT,0,0);
		List<Installment> installments =  calculator.calculateInstalments(timetable);
		Assertions.assertEquals(InstallmentType.CONSTANT,timetable.getInstallmentType());
		Assertions.assertEquals(250,installments.get(0).getAmount());
		Assertions.assertEquals(250,installments.get(1).getAmount());
		Assertions.assertEquals(250,installments.get(2).getAmount());
	}

	@Test
	public void timetable_Should_Have_DECREASING_Installments(){
		Timetable timetable = new Timetable(2000,5,InstallmentType.DECREASING,0.01,0);
		List<Installment> installments =  calculator.calculateInstalments(timetable);
		Assertions.assertEquals(InstallmentType.DECREASING,timetable.getInstallmentType());
		Assertions.assertTrue(installments.get(0).getAmount() > installments.get(1).getAmount());
		Assertions.assertTrue(installments.get(1).getAmount() > installments.get(2).getAmount());
		Assertions.assertTrue(installments.get(2).getAmount() > installments.get(3).getAmount());
	}

}
