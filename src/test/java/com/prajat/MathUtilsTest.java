package com.prajat;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@DisplayName("Testing for MathUtils")
class MathUtilsTest {
	
	MathUtils calculator;
	TestInfo testInfo;
	TestReporter testReporter;
	// if per_class lifecycle is set no need of this metyhod to be static, for default per_method this 
	// method should be static
//	@BeforeAll
//	void beforeAllInit() {
//		System.out.println("This runs once before all");
//	}
	
	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		calculator = new MathUtils();
		
		testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tag " + testInfo.getTags());
	}
	
//	@AfterEach
//	void cleanUp() {
//		System.out.println("Cleaningup....");
//	}
//	
	
	@Nested // this is useful for testing multiple scenarios of a single method
	@Tag("Math")
	class AddTest {
		
		@Test
		@DisplayName("Testing add method for +ve")
		void testAddPositive() {
			int expected = 3;
			int actual = calculator.add(1, 2);
			assertEquals(expected, actual, "the method should add 2 numbers");
		}
		

		@Test
		@DisplayName("Testing add method for -ve")
		void testAddNegative() {
			int expected = -3;
			int actual = calculator.add(-1, -2);
			assertEquals(expected, actual, () -> "the method should sum " + expected + " but returned " + actual);
		}
		
	}
	
	@Test
	@Tag("Math")
	@DisplayName("Testing multiply method")
	void testAddNegative() {
		//assertEquals(2, calculator.multiply(2, 1), "the method should add 2 numbers");
		
		assertAll(
					()-> assertEquals(6, calculator.multiply(2, 3)),
					()-> assertEquals(0, calculator.multiply(0, 3)),	
					()-> assertEquals(-9, calculator.multiply(-3, 3))
				);
	}
	
	@Test
	@Tag("Math")
	void testDivide() {
		
		assumeTrue(true); // this test will only run parameterr passed is true
		
		assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0), "Divide by zero should throw");
		
	}

	
	//@Test
	@RepeatedTest(3)
	@Tag("Circle")
	void testCoputeCircleArea(RepetitionInfo info) {
		System.out.println(info);
		if(info.getCurrentRepetition() == 1) {
			// do something
		} else if(info.getCurrentRepetition() == 2) {
			// do something
		} 
		assertEquals(Math.PI*10*10, calculator.computeCircleArea(10), "the method computes area of a circle");
		
	}
	
	@Test
	@Disabled
	@DisplayName("TDD method, should not run")
	@EnabledOnOs(OS.WINDOWS) // this test will only run on windows
	void testDisabled() {
		fail("this test is disabled");
	}
}
