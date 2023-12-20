package com.kbt1.ollilove.transferservice.repository;

import com.kbt1.ollilove.transferservice.domain.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findAllBySenderIdOrReceiverIdOrderByRegDateAsc(Long senderId, Long receiverId);
    Transfer findByTransferId(Long transferId);

}
