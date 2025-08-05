package com.example.application_management_system.repository;

import com.example.application_management_system.entity.Applicant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationCrudRepository extends CrudRepository<Applicant, Long> {
}
