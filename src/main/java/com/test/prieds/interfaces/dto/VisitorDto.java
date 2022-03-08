package com.test.prieds.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitorDto {
	private String id;
	private String name;
	private String address;
	private String phoneNumber;
	private String illness;
	private String visitDate;
	private String queueId;
	private String queueCreatedDate;
}
