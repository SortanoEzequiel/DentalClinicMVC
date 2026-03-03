package com.dh.DentalClinicMVC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dh.DentalClinicMVC.entity.Patient;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long>{

}
