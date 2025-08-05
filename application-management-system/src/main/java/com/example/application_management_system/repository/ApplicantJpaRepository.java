package com.example.application_management_system.repository;

import com.example.application_management_system.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicantJpaRepository extends JpaRepository<Applicant, Long> {
    List<Applicant> findByStatusOrderByNameDesc(String status);

    @Query("SELECT a FROM Applicant a WHERE a.name LIKE %:name%")
    List<Applicant> findApplicantsByPartialNameOrderByNameAsc(@Param("name") String name); // can skip OrderBy as well
}
