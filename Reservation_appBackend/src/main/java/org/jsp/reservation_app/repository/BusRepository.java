package org.jsp.reservation_app.repository;

import org.jsp.reservation_app.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Integer> {

//	Optional<Bus> findByBusNumber(String bus_number);

//	@Query("select b from Bus b where b.name=?1")
//	List<Bus> findByBusName(String name);

//	List<Bus> findByDate(LocalDate date_of_departure);

}