package com.test.prieds.interfaces.web;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.prieds.interfaces.dto.VisitorDto;
import com.test.prieds.interfaces.dto.VisitorSaveDto;
import com.test.prieds.model.Visitor;
import com.test.prieds.service.VisitorService;

@CrossOrigin(origins = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/visitor")
public class VisitorController {
	private VisitorService visitorService;
	
	public VisitorController(VisitorService visitorService) {
		this.visitorService = visitorService;
	}
	
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VisitorDto>> getAllVisitors() {
		return ResponseEntity.ok(visitorService.getAll());
	}
	
	@GetMapping(value = "/byNameAndVisitDate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VisitorDto>> getAllVisitorsByNameAndVisitDate(
			@RequestParam String name,
			@RequestParam(name = "visitDate") String visitDateStr) {
		LocalDate visitDate = LocalDate.parse(visitDateStr);
		return ResponseEntity.ok(visitorService.getAllByNameAndVisitDate(name, visitDate));
	}
	
	@GetMapping(value = "/byName", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VisitorDto>> getAllVisitorsByName(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "page") int page,
			@RequestParam(name = "size") int size
			) {
		int actualPage = page > 0 ? page - 1 : 0;
		
		Page<Visitor> pageObj = visitorService.getAllByName(name, actualPage, size);
		List<VisitorDto> pageObjContent = pageObj.getContent().stream()
				.map(visitor -> {
					return VisitorDto.builder()
							.id(visitor.getId().toString())
							.name(visitor.getName())
							.address(visitor.getAddress())
							.phoneNumber(visitor.getPhoneNumber())
							.illness(visitor.getIllness())
							.visitDate(visitor.getVisitDate().toString())
							.queueId(visitor.getVisitorQueue().getQueueId())
							.queueCreatedDate(visitor.getVisitorQueue().getCreatedDate().toString())
							.build();
				})
				.collect(Collectors.toList());
		
		
		return ResponseEntity.status(HttpStatus.OK)
				.header("totalElements", String.valueOf(pageObj.getTotalElements()))
				.body(pageObjContent);
	}
	
	@GetMapping(value = "/byNameOrVisitDate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VisitorDto>> getAllVisitorsByNameOrVisitDate(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "visitDate") String visitDateStr,
			@RequestParam(name = "page") int page,
			@RequestParam(name = "size") int size
			) {
		int actualPage = page > 0 ? page - 1 : 0;
		LocalDate visitDate = LocalDate.parse(visitDateStr);
		
		return ResponseEntity.ok(visitorService.getAllByNameOrVisitDate(name, visitDate, actualPage, size));
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VisitorDto> getVisitorById(@PathVariable Long id) {
		return ResponseEntity.ok(visitorService.getById(id));
	}
	
	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Boolean>> saveVisitor(@RequestBody VisitorSaveDto visitorSave) {
		Boolean isSuccess = false;
		
		try {
			visitorService.save(visitorSave);
			isSuccess = true;
		} catch (Exception e) {
			isSuccess = false;
		}
		
		return ResponseEntity.ok(Collections.singletonMap("success", isSuccess));
		
	}
}
