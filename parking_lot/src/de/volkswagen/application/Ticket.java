package de.volkswagen.application;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class Ticket {
	
	private static String[] charsList = {"a","b","c","d","e","f","g","h","i","j","k",
			"l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
			"1","2","3","4","5","6","7","8","9","0"};
	
	private LocalDateTime timeEntered = LocalDateTime.now();
	private String licensePlateNumber;
	private Color carColor;
	private String ticketID;
	private int parkingLotNumber;
	
	public Ticket(String licensePlateNumber, Color carColor, int parkingLotNumber) {
		this.licensePlateNumber = licensePlateNumber;
		this.carColor = carColor;
		this.parkingLotNumber = parkingLotNumber;
		this.ticketID = randomTicketID();
	}

	private String randomTicketID() {
		String ticketID = "";
		for(int i = 0; i < 8; i++) {
			int rndNumber = (int) (Math.floor(Math.random()*charsList.length));
			ticketID += charsList[rndNumber];
		}
		return ticketID;
	}

	public LocalDateTime getTimeEntered() {
		return timeEntered;
	}

	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	public Color getCarColor() {
		return carColor;
	}

	public String getTicketID() {
		return ticketID;
	}

	public int getParkingLotNumber() {
		return parkingLotNumber;
	}
		
}
