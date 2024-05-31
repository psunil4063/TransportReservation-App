package org.jsp.reservation_app.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusResponse {
	private int id;
	private String name;
	private String bus_number;
	private String from_location;
	private String to_location;
	private int number_of_seat;
	private LocalDate date_of_departure;

}