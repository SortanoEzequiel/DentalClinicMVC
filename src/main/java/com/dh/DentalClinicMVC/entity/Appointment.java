package com.dh.DentalClinicMVC.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity 
@Table(name = "appointment")
public class Appointment {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id")
    private Long id;
	
	@ManyToOne
    private Patient patient;
	
	@ManyToOne
    private Dentist dentist;
	
	@Column(name = "date")
    private LocalDate date;


    public Appointment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
