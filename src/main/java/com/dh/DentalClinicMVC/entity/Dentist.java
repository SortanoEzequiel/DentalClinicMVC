package com.dh.DentalClinicMVC.entity;



import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity 
@Table(name = "dentists")
public class Dentist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dentist_id")
    private Long id;
	@Column(name = "registration")
    private Integer registration;
	@Column(name = "name")
    private String name;
	@Column(name = "last_name")
    private String lastName;
	
	@OneToMany(mappedBy = "dentist")
	private Set<Appointment> appointments = new HashSet<>();
    public Dentist() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRegistration() {
        return registration;
    }

    public void setRegistration(Integer registration) {
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}
    
}
