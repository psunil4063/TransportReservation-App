package org.jsp.reservation_app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@NotBlank
@AllArgsConstructor
@Builder
public class AdminResponse {
	private int id;
	private String name;
	private long phone;
	private String email;
	private String gst_number;
	private String travels_name;
	private String password;

}