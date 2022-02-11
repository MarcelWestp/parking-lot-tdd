package de.volkswagen.application;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.paint.Color;

public class ParkingLot {

	private int sizeParkingLot = 0;
	private HashMap<Integer, Ticket> parkingLots = new HashMap<>();

	public ParkingLot(int sizeParkingLot) {
		if (sizeParkingLot >= 500) {
			this.sizeParkingLot = 500;
		}else {
			this.sizeParkingLot = sizeParkingLot;
		}
		for (int i = 0; i < this.sizeParkingLot; i++) {
			parkingLots.put(i +1, null);
		}

	}

	public int getSizeParkingLot() {
		return sizeParkingLot;
	}

	public int parkingFee(int hours, int hoursDailyFee) {
		if (hours >= hoursDailyFee) {
			return 30;
		} else {
			return hours * 3;
		}
	}

	public int parkingLotNumber(int parkingLotNumber) {
		if (parkingLotNumber < 1 || parkingLotNumber > sizeParkingLot) {
			throw new RuntimeException("Parking Lot Number must between 1 and " + sizeParkingLot);
		}
		Ticket ticket = parkingLots.get(parkingLotNumber);
		return parkingLotNumber;
	}

	public Ticket createTicket(String licensePlate, Color carColor) {
		int availableParkingSlot = 0;
		for(int i = 1; i <= this.sizeParkingLot; i++) {
			if(this.parkingLots.get(i) == null) {
				availableParkingSlot = i;
				break;
			}else if(this.sizeParkingLot == i) {
				throw new RuntimeException("No available parking slot");
			}
		}
		Ticket ticket = new Ticket(licensePlate, carColor, availableParkingSlot);
		this.parkingLots.replace(availableParkingSlot, ticket);
		
		return ticket;
	}

}
