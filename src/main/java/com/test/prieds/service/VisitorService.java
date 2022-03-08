package com.test.prieds.service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.prieds.interfaces.dto.VisitorDto;
import com.test.prieds.interfaces.dto.VisitorSaveDto;
import com.test.prieds.model.Visitor;
import com.test.prieds.model.VisitorQueue;
import com.test.prieds.persistence.VisitorRepository;

@Service
public class VisitorService {
	private VisitorRepository visitorRepo;
	private VisitorQueueService visitorQueueService;
	
	public VisitorService(VisitorRepository visitorRepo, VisitorQueueService visitorQueueService) {
		this.visitorRepo = visitorRepo;
		this.visitorQueueService = visitorQueueService;
	}
	
	public List<VisitorDto> getAll() {
		return visitorRepo.findAll().stream()
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
		
	}
	
	public List<VisitorDto> getAllByNameAndVisitDate(String name, LocalDate visitDate) {
		return visitorRepo.findByNameContainingAndVisitDate(name, visitDate).stream()
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
		
	}
	
	public List<VisitorDto> getAllByNameOrVisitDate(String name, LocalDate visitDate, int page, int size) {
		return visitorRepo.findByNameContainingOrVisitDate(name, visitDate, PageRequest.of(page, size)).stream()
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
		
	}
	
	public Page<Visitor> getAllByName(String name, int page, int size) {
		return visitorRepo.findByNameContaining(name, PageRequest.of(page, size));
	}
	
	public VisitorDto getById(Long id) {
		Optional<Visitor> visitorOpt = visitorRepo.findById(id);
		if (visitorOpt.isPresent()) {
			Visitor visitor = visitorOpt.get();
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
		}
		
		return null;
	}
	
	@Transactional
	public void save(VisitorSaveDto visitorSave) {
		String newQueueId = visitorQueueService.generateQueueId();
		VisitorQueue newQueue = VisitorQueue.builder()
				.queueId(newQueueId)
				.createdDate(OffsetDateTime.now())
				.build();
		VisitorQueue savedQueue = visitorQueueService.save(newQueue);
		
		Visitor newVisitor = Visitor.builder()
				.name(visitorSave.getName())
				.address(visitorSave.getAddress())
				.phoneNumber(visitorSave.getPhoneNumber())
				.illness(visitorSave.getIllness())
				.visitDate(LocalDate.now())
				.visitorQueue(savedQueue)
				.build();
		visitorRepo.save(newVisitor);
	}
}
