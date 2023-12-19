package com.kbt1.ollilove.transferservice.repository;

import com.kbt1.ollilove.transferservice.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findBySenderIdOrReceiverIdOrderByRegDateDesc(Long senderId, Long receiverId);
}
