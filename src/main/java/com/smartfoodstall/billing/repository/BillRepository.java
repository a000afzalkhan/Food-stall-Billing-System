package com.smartfoodstall.billing.repository;

import com.smartfoodstall.billing.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface BillRepository extends JpaRepository<Bill, Long> {

    long countByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    @Query("select coalesce(sum(b.grandTotal), 0) from Bill b where b.createdAt between :start and :end")
    BigDecimal totalSalesBetween(LocalDateTime start, LocalDateTime end);
}
