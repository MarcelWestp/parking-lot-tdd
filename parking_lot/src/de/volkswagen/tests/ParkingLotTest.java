package de.volkswagen.tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import de.volkswagen.application.ParkingLot;
import de.volkswagen.application.Ticket;
import javafx.scene.paint.Color;

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
	@DisplayName("getParkingLotNumber returnParkingLotNumber elseThrowRunTimeException")
	void getParkingLotNumberTest(int parkingLotNumber) {

		// GIVEN
		// Parking spaces are numbered from 1 to the maximum number of spaces on the lot
		ParkingLot parkingLot = new ParkingLot(100);
		int expected = parkingLotNumber;

		// THEN
		if (parkingLotNumber > 0 && parkingLot.getSizeParkingLot() >= parkingLotNumber) {
			// WHEN parkingLotNumber is correct
			int actual = parkingLot.parkingLotNumber(parkingLotNumber);
			assertEquals(expected, actual);
		} else {
			// WHEN parkingLotNumber is incorrect
			RuntimeException expectedException = Assertions.assertThrows(RuntimeException.class, () -> {
				parkingLot.parkingLotNumber(parkingLotNumber);
			}, "RuntimeException Error was expected");
			assertEquals("Parking Lot Number must between 1 and " + parkingLot.getSizeParkingLot(),
					expectedException.getMessage());
		}
	}
	
	@ParameterizedTest
	@ValueSource(ints = { 1, 2})
	@DisplayName("createTicket returnNewTicket thorwsRunTimeExceptionIfNoSlotIsAvailable")
	void createTicketTest(int parkingSize) {

		// GIVEN
		// When a car enters the parking lot, a ticket is created. The ticket issuing process 
		// includes documenting the time entered, license plate number and the color of the car 
		// and allocating an available parking slot to the car. The ticket ID should be a random 
		// sequence of numbers and characters.
		ParkingLot parkingLot = new ParkingLot(parkingSize);
		
		// WHEN
		String licensePlate = "LA-805";
		Ticket ticket =parkingLot.createTicket(licensePlate, Color.RED);
		
		// THEN
		assertEquals(licensePlate, ticket.getLicensePlateNumber());
		assertEquals(Color.RED, ticket.getCarColor());
//		System.out.println(ticket.getTimeEntered() + " " + ticket.getTicketID());
		if(parkingSize == 1) {
			RuntimeException expectedException = Assertions.assertThrows(RuntimeException.class, () -> {
				Ticket ticket2 =parkingLot.createTicket(licensePlate, Color.RED);
			}, "RuntimeException Error was expected");
			assertEquals("No available parking slot", expectedException.getMessage());
		}else {
			Ticket ticket2 =parkingLot.createTicket(licensePlate, Color.RED);
			assertEquals(licensePlate, ticket2.getLicensePlateNumber());
		}
	}

}
