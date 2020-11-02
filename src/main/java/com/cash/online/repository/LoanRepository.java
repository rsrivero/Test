package com.cash.online.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cash.online.model.Loan;

public interface LoanRepository extends PagingAndSortingRepository<Loan, Long>{

	Page<Loan> findByUserId(Long userId, Pageable pagination);

}
