package com.test.prieds.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.test.prieds.interfaces.dto.VisitorQueueDto;
import com.test.prieds.model.VisitorQueue;
import com.test.prieds.persistence.VisitorQueueRepository;

@Service
public class VisitorQueueService {
	private VisitorQueueRepository visitorQueueRepo;
	
	public VisitorQueueService(VisitorQueueRepository visitorQueueRepository) {
		this.visitorQueueRepo = visitorQueueRepository;
	}
	
	public VisitorQueueDto getById(Long id) {
		Optional<VisitorQueue> visitorQueueOpt = visitorQueueRepo.findById(id);
		if (visitorQueueOpt.isPresent()) {
			VisitorQueue visitorQueue = visitorQueueOpt.get();
			return VisitorQueueDto.builder()
					.id(visitorQueue.getId().toString())
					.queueId(visitorQueue.getQueueId())
					.createdDate(visitorQueue.getCreatedDate().toString())
					.build();
		}
		return null;
	}
	
	public String generateQueueId() {
		String zero = "000";
		String currentQueueId = visitorQueueRepo.getCurrentQueueId();
		String numberStr = currentQueueId == null ? "000" : currentQueueId.substring(1);
		int number = Integer.parseInt(numberStr);
		
		if (number >= 999) {
			throw new IllegalArgumentException("antrean hari ini penuh");
		}
		
		number++;
		String numberResultStr = String.valueOf(number);
		return String.format(
				"A%s",
				zero.substring(0, zero.length() - numberResultStr.length()) + numberResultStr);
		
	}
	
	public VisitorQueue save(VisitorQueue queue) {
		return visitorQueueRepo.save(queue);
	}
}
