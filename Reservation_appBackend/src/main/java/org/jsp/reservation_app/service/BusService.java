package org.jsp.reservation_app.service;

import java.util.Optional;

import org.jsp.reservation_app.dao.AdminDao;
import org.jsp.reservation_app.dao.BusDao;
import org.jsp.reservation_app.dto.BusRequest;
import org.jsp.reservation_app.dto.BusResponse;
import org.jsp.reservation_app.dto.ResponseStructure;
import org.jsp.reservation_app.exception.AdminNotFoundException;
import org.jsp.reservation_app.exception.BusNotFoundException;
import org.jsp.reservation_app.model.Admin;
import org.jsp.reservation_app.model.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BusService {
	@Autowired
	private BusDao busDao;

	@Autowired
	private AdminDao adminDao;

	public ResponseEntity<ResponseStructure<BusResponse>> saveBus(BusRequest busRequest, int admin_id) {
		Optional<Admin> rec = adminDao.findById(admin_id);
		if (rec.isPresent()) {
			Admin admin = rec.get();
			busRequest.setAdmin(admin);
			admin.getBus().add(mapToBus(busRequest));
			ResponseStructure<BusResponse> structure = new ResponseStructure<>();
			structure.setData(mapToBusResponse(busDao.saveBus(mapToBus(busRequest))));
			adminDao.saveAdmin(admin);
			structure.setMessage("Bus Added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		throw new AdminNotFoundException("Can't Add Bus as Admin Id is Invalid");
	}

	public ResponseEntity<ResponseStructure<BusResponse>> updateBus(BusRequest busRequest, int id) {
		ResponseStructure<BusResponse> structure = new ResponseStructure<>();
		Optional<Bus> rec = busDao.findById(id);

		if (rec.isPresent()) {
			Bus db = rec.get();
			db.setBus_number(busRequest.getBus_number());
			db.setDate_of_departure(busRequest.getDate_of_departure());
			db.setFrom_location(busRequest.getFrom_location());
			db.setName(busRequest.getName());
			db.setNumber_of_seat(busRequest.getNumber_of_seat());
			db.setTo_location(busRequest.getTo_location());
			structure.setData(mapToBusResponse(busDao.saveBus(db)));
			structure.setMessage("Bus Updated");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new BusNotFoundException("Can't Update Bus as Id is Invalid");
	}

	public ResponseEntity<ResponseStructure<BusResponse>> findById(int id) {
		ResponseStructure<BusResponse> structure = new ResponseStructure<>();
		Optional<Bus> rec = busDao.findById(id);

		if (rec.isPresent()) {
			structure.setMessage("Bus Found");
			structure.setData(mapToBusResponse(rec.get()));
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new BusNotFoundException("Can't Find Bus as Id is Invalid");
	}

	public ResponseEntity<ResponseStructure<String>> deleteBus(int id) {
		Optional<Bus> rec = busDao.findById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();

		if (rec.isPresent()) {
			busDao.deleteBus(id);
			structure.setData("BUs Found");
			structure.setMessage("Bus Deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new BusNotFoundException("Can't Delete Bus as Id is Invalid");
	}

	private Bus mapToBus(BusRequest busRequest) {
		return Bus.builder().name(busRequest.getName()).bus_number(busRequest.getBus_number())
				.from_location(busRequest.getFrom_location()).to_location(busRequest.getTo_location())
				.number_of_seat(busRequest.getNumber_of_seat()).date_of_departure(busRequest.getDate_of_departure())
				.admin(busRequest.getAdmin()).build();
	}

	private BusResponse mapToBusResponse(Bus bus) {
		return BusResponse.builder().id(bus.getId()).name(bus.getName()).bus_number(bus.getBus_number())
				.from_location(bus.getFrom_location()).to_location(bus.getTo_location())
				.number_of_seat(bus.getNumber_of_seat()).date_of_departure(bus.getDate_of_departure()).build();
	}
}