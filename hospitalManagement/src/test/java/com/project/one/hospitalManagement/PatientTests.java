package com.project.one.hospitalManagement;

import com.project.one.hospitalManagement.entity.Patient;
import com.project.one.hospitalManagement.repository.PatientRepository;
import com.project.one.hospitalManagement.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testPatientRepository() {
        List<Patient>testPatient = patientRepository.findAll();
        System.out.println(testPatient);
    }
}
