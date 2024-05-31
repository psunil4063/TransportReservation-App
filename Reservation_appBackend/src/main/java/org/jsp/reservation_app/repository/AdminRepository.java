package org.jsp.reservation_app.repository;

import java.util.Optional;

import org.jsp.reservation_app.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Optional<Admin> findByPhoneAndPassword(long phone, String password);

	Optional<Admin> findByEmailAndPassword(String email, String password);

}