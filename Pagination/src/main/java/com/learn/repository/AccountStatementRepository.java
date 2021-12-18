package com.learn.repository;

import com.learn.entity.AccountStatement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.util.List;

public interface AccountStatementRepository extends JpaRepository<AccountStatement, Long> {

    /*
         https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
     */
    List<AccountStatement> findByAccountNumber(BigDecimal accountNumber);

    List<AccountStatement> findByAccountNumber(BigDecimal accountNumber, Sort sort);

    Page<AccountStatement> findByAccountNumber(BigDecimal accountNumber, Pageable pageRequest);

}
