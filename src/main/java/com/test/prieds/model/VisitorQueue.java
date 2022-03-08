package com.test.prieds.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "visitor_queue")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitorQueue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "queue_id", length = 4)
	private String queueId;
	
	@Column(name = "created_date")
	private OffsetDateTime createdDate;
}
