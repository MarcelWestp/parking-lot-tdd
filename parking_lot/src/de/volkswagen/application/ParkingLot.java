package de.volkswagen.application;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

	private int sizeParkingLot = 0;
	private List<Integer> parkingLots = new ArrayList<>();

	public ParkingLot(int sizeParkingLot) {
		if (sizeParkingLot >= 500) {
			this.sizeParkingLot = 500;
		}else {
			this.sizeParkingLot = sizeParkingLot;
		}
		for (int i = 0; i < this.sizeParkingLot; i++) {
			parkingLots.add(i + 1);
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
		return parkingLots.get(parkingLotNumber - 1);

	}

}
