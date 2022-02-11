package de.volkswagen.tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Assertions;
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
	@ValueSource(ints = { 0, 1, 100, Integer.MIN_VALUE, Integer.MAX_VALUE })
	@DisplayName("getSizeParkingLotTest returnsSizes")
	void getSizeParkingLotTest(int sizes) {

		// GIVEN A parking lot can hold up to 'n' cars at any given point in time.
		ParkingLot parkingLot = new ParkingLot(sizes);
		int expected = 0;
		if (sizes >= 500) {
			expected = 500;
		} else {
			expected = sizes;
		}

		// WHEN
		int actual = parkingLot.getSizeParkingLot();

		// THEN
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 100, Integer.MIN_VALUE, Integer.MAX_VALUE })
	@DisplayName("getParkingFeeTest returnParkingFee")
	void getParkingFeeTest(int hours) {

		// GIVEN
		// Parking in this lot has a per whole hour fee.
		// Customers who spend less than 1 whole hour, are not charged anything.
		// Parking in this lot has a daily fee based on a number of hours chosen by the
		// owner.
		// The daily fee would kick in after the prescribed number of whole hours has
		// passed.
		ParkingLot parkingLot = new ParkingLot(42);
		int expected = 0;
		if (hours >= 8) {
			expected = 30;
		} else {
			expected = hours * 3;
		}

		// WHEN
		int actual = parkingLot.parkingFee(hours, 8);

		// THEN
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 100, Integer.MAX_VALUE })
	@DisplayName("Foo")
	void foo(int parkingLotNumber) {

		// GIVEN
		// Parking spaces are numbered from 1 to the maximum number of spaces on the lot
		ParkingLot parkingLot = new ParkingLot(100);
		int expected = parkingLotNumber;
		// WHEN

		// THEN
		if (parkingLotNumber > 0 && parkingLot.getSizeParkingLot() >= parkingLotNumber) {
			int actual = parkingLot.parkingLotNumber(parkingLotNumber);
			assertEquals(expected, actual);
		} else {
			RuntimeException expectedException = Assertions.assertThrows(RuntimeException.class, () -> {
				parkingLot.parkingLotNumber(parkingLotNumber);
			}, "RuntimeException Error was expected");
			assertEquals("Parking Lot Number must between 1 and " + parkingLot.getSizeParkingLot(),
					expectedException.getMessage());
		}
	}

}
