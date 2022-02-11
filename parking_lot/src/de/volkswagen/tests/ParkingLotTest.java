package de.volkswagen.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import de.volkswagen.application.ParkingLot;

class ParkingLotTest {
	
//			Zero        : Zero case             : getSizeParkingLot() == 0
//			One         : One case              : getSizeParkingLot() == 1
//			Many        : Many case             : getSizeParkingLot() == 100
//			Boundaries  : Boundary cases        : getSizeParkingLot() == Integer.MIN_VALUE && Integer.MAX_VALUE
//			Interfaces  : Interface definitions : ParkingLot.getSizeParkingLot() <--- interface
//			Exceptions  : Exception cases       : XXXXX
//			Simple      : Simple solutions      : Does my code make sense?

	@ParameterizedTest
	@ValueSource(ints = {0, 1, 100, Integer.MIN_VALUE, Integer.MAX_VALUE})
	@DisplayName("getSizeParkingLotTest_returnsSizes")
	void getSizeParkingLotTest(int sizes) {
		
		// GIVEN A parking lot can hold up to 'n' cars at any given point in time.
		ParkingLot parkingLot = new ParkingLot(sizes);
		int expected = sizes;
		
		// WHEN
		int actual = parkingLot.getSizeParkingLot();
		
		// THEN
		assertEquals(expected, actual);	
	}

}
