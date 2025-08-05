package com.example.application_management_system.repository;

import com.example.application_management_system.entity.Applicant;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApplicationPagingAndSortingRepository extends PagingAndSortingRepository<Applicant, Long> {
}
