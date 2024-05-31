package org.jsp.reservation_app.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	private String name;
	private long phone;
	private String email;
	private int age;
	private String gender;
	private String password;
}

