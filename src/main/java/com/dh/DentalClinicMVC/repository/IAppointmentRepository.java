package com.dh.DentalClinicMVC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dh.DentalClinicMVC.entity.Appointment;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long>{

}
