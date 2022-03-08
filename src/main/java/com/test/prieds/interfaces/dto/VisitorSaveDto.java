package com.test.prieds.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitorSaveDto {
	private String name;
	private String address;
	private String phoneNumber;
	private String illness;
}
