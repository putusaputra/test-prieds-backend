package com.test.prieds.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "visitor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Visitor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "address", length = 100)
	private String address;
	
	@Column(name = "phone_number", length = 20)
	private String phoneNumber;
	
	@Column(name = "illness", length = 500)
	private String illness;
	
	@Column(name = "visit_date")
	private LocalDate visitDate;
	
	@OneToOne
	@JoinColumn(name = "visitor_queue_id", referencedColumnName = "id")
	private VisitorQueue visitorQueue;
}
