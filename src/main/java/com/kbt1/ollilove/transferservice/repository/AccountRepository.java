package com.kbt1.ollilove.transferservice.repository;

import com.kbt1.ollilove.transferservice.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
