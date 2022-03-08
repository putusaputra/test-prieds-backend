package com.test.prieds.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.prieds.model.VisitorQueue;

public interface VisitorQueueRepository extends JpaRepository<VisitorQueue, Long> {
	@Query(value = "SELECT MAX(q.queueId) FROM VisitorQueue q WHERE date(q.createdDate) = CURRENT_DATE")
	public String getCurrentQueueId();
}
