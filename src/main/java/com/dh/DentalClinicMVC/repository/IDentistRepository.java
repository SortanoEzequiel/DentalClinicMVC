package com.dh.DentalClinicMVC.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dh.DentalClinicMVC.entity.Dentist;

@Repository
public interface IDentistRepository extends JpaRepository<Dentist, Long>{

	//@Query("SELECT d FROM Dentist d WHERE d.registration=?1")
	Optional<Dentist> findByRegistration(Integer registration);
	
}
