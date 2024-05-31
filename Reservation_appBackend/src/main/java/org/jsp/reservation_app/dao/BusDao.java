package org.jsp.reservation_app.dao;
import java.util.Optional;

import org.jsp.reservation_app.model.Bus;
import org.jsp.reservation_app.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BusDao {
	@Autowired
	private BusRepository repository;

	public Bus saveBus(Bus bus) {
		return repository.save(bus);
	}

	public Optional<Bus> findById(int id) {
		return repository.findById(id);
	}

//	public List<Bus> findByBusName(String name) {
//		return repository.findByBusName(name);
//	}

//	public Optional<Bus> findBYBusNumber(String bus_number) {
//		return repository.findByBusNumber(bus_number);
//	}

//	public List<Bus> findByDate(LocalDate date_of_departure) {
//		return repository.findByDate(date_of_departure);
//	}

	public void deleteBus(int id) {
		repository.deleteById(id);
	}

}