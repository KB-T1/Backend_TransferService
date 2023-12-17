package com.kbt1.ollilove.transferservice.repository;

import com.kbt1.ollilove.transferservice.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
