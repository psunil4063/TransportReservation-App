package org.jsp.reservation_app.service;

import java.util.Optional;

import org.jsp.reservation_app.dao.AdminDao;
import org.jsp.reservation_app.dto.AdminRequest;
import org.jsp.reservation_app.dto.AdminResponse;
import org.jsp.reservation_app.dto.ResponseStructure;
import org.jsp.reservation_app.exception.AdminNotFoundException;
import org.jsp.reservation_app.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;

	public ResponseEntity<ResponseStructure<AdminResponse>> saveAdmin(AdminRequest adminRequest) {
		ResponseStructure<AdminResponse> structure = new ResponseStructure<>();
		structure.setMessage("Admin Saved");
		structure.setData(mapToAdminResponse(adminDao.saveAdmin(mapToAdmin(adminRequest))));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}

	/**
	 * This method will accept AdminRequest(DTO) and Admin Id and Update the Admin
	 * in the Database if identifier is valid.
	 * 
	 * @param AdminRequest
	 * @param int
	 * @throw {@code AdminNotFoundException} if Identifier is Invalid
	 */

	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest, int id) {
		ResponseStructure<AdminResponse> structure = new ResponseStructure<>();
		Optional<Admin> rec = adminDao.findById(id);

		if (rec.isPresent()) {
			Admin db = rec.get();
			db.setEmail(adminRequest.getEmail());
			db.setGst_number(adminRequest.getGst_number());
			db.setName(adminRequest.getName());
			db.setPhone(adminRequest.getPhone());
			db.setPassword(adminRequest.getPassword());
			db.setTravels_name(adminRequest.getTravels_name());
			structure.setData(mapToAdminResponse(adminDao.saveAdmin(db)));
			structure.setMessage("Admin Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new AdminNotFoundException("Can't Update Admin as Id is Invalid");
	}

	public ResponseEntity<ResponseStructure<AdminResponse>> findById(int id) {
		ResponseStructure<AdminResponse> structure = new ResponseStructure<>();
		Optional<Admin> db = adminDao.findById(id);

		if (db.isPresent()) {
			structure.setData(mapToAdminResponse(db.get()));
			structure.setMessage("Admin Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Invalid Admin Id");
	}

	public ResponseEntity<ResponseStructure<AdminResponse>> verifyAdmin(long phone, String password) {
		ResponseStructure<AdminResponse> structure = new ResponseStructure<>();
		Optional<Admin> db = adminDao.verifyAdmin(phone, password);

		if (db.isPresent()) {
			structure.setData(mapToAdminResponse(db.get()));
			structure.setMessage("Verification Succesfull");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Invalid Phone Number and Password");
	}

	public ResponseEntity<ResponseStructure<AdminResponse>> verifyAdmin(String email, String password) {
		ResponseStructure<AdminResponse> structure = new ResponseStructure<>();
		Optional<Admin> db = adminDao.verifyAdmin(email, password);

		if (db.isPresent()) {
			structure.setData(mapToAdminResponse(db.get()));
			structure.setMessage("Verification Succesfull");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Invalid Email Id and Password");
	}

	public ResponseEntity<ResponseStructure<String>> deleteAdmin(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Admin> db = adminDao.findById(id);

		if (db.isPresent()) {
			adminDao.deleteAdmin(id);
			structure.setMessage("Admin Deleted");
			structure.setData("Admin Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Can't Delete Admin as Id is Invalid");
	}

	private Admin mapToAdmin(AdminRequest adminRequest) {
		return Admin.builder().name(adminRequest.getName()).phone(adminRequest.getPhone())
				.email(adminRequest.getEmail()).gst_number(adminRequest.getGst_number())
				.travels_name(adminRequest.getTravels_name()).password(adminRequest.getPassword()).build();
	}

	private AdminResponse mapToAdminResponse(Admin admin) {
		return AdminResponse.builder().id(admin.getId()).name(admin.getName()).phone(admin.getPhone())
				.email(admin.getEmail()).gst_number(admin.getGst_number()).travels_name(admin.getTravels_name())
				.password(admin.getPassword()).build();
	}
}