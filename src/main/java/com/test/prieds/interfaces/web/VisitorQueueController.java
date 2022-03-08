package com.test.prieds.interfaces.web;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;

import com.test.prieds.interfaces.dto.VisitorQueueDto;
import com.test.prieds.service.VisitorQueueService;

@CrossOrigin(CorsConfiguration.ALL)
@RestController
@RequestMapping("/visitor-queue")
public class VisitorQueueController {
	private VisitorQueueService visitorQueueService;
	
	public VisitorQueueController(VisitorQueueService visitorQueueService) {
		this.visitorQueueService = visitorQueueService;
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VisitorQueueDto> getById(@PathVariable Long id) {
		return ResponseEntity.ok(visitorQueueService.getById(id));
	}
}
