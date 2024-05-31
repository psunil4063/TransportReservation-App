package org.jsp.reservation_app.repository
;
import java.util.Optional;

import org.jsp.reservation_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByPhoneAndPassword(long phone, String password);

	Optional<User> findByEmailAndPassword(String email, String password);
}