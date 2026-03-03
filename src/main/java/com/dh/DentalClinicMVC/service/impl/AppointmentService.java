package com.dh.DentalClinicMVC.service.impl;

import com.dh.DentalClinicMVC.dto.AppointmentDTO;
import com.dh.DentalClinicMVC.entity.Appointment;
import com.dh.DentalClinicMVC.entity.Dentist;
import com.dh.DentalClinicMVC.entity.Patient;
import com.dh.DentalClinicMVC.exception.ResourceNotFoundException;
import com.dh.DentalClinicMVC.repository.IAppointmentRepository;
import com.dh.DentalClinicMVC.service.IAppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService implements IAppointmentService{
	
	private IAppointmentRepository appointmentRepository;
	
	@Autowired
	public AppointmentService(IAppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}

	@Override
	public AppointmentDTO save(AppointmentDTO appointmentDTO) {
		       //mapear nuestras entidades como DTO a mano//
		
		//instanciar una entidad de turno 
		Appointment appointmentEntity = new Appointment();
		
		//instanciar una entidad paciente 
		Patient patientEntity = new Patient();
		patientEntity.setId(appointmentDTO.getPatient_id());
		
		//instanciar una entidad dentist
		Dentist dentistEntity = new Dentist();
		dentistEntity.setId(appointmentDTO.getDentist_id());
		
		//seteamos el paciente y el odontologo a nuestra entidad turno
		appointmentEntity.setPatient(patientEntity);
		appointmentEntity.setDentist(dentistEntity);
		
		//convertir el string del turnoDto a LocalDate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(appointmentDTO.getDate(), formatter);
		
		//seteamos la fecha
		appointmentEntity.setDate(date); 
		
		//persistir a la BD
		appointmentRepository.save(appointmentEntity);
		
		//vamos a trabajar con el DTO que debemos devolver
		//generar una instancia de turno DTO
		AppointmentDTO appointmentDTOToReturn = new AppointmentDTO();
		
		//seteamos los datos de la entidad que persistimos
		appointmentDTOToReturn.setId(appointmentEntity.getId());
		appointmentDTOToReturn.setDate(appointmentEntity.getDate().toString());
		appointmentDTOToReturn.setDentist_id(appointmentEntity.getDentist().getId());
		appointmentDTOToReturn.setPatient_id(appointmentEntity.getPatient().getId());
		return appointmentDTOToReturn;
	}

	@Override
	public Optional<AppointmentDTO> findById(Long id) throws ResourceNotFoundException {
		
		//vamos a buscar la entidad turno por id a la BD
		Optional<Appointment> appointmentToLookFor = appointmentRepository.findById(id);
		
		//intstanciamos el DTO
		Optional<AppointmentDTO> appointmentDTO = null;
		
		if(appointmentToLookFor.isPresent()){
			Appointment appointment = appointmentToLookFor.get();
			//creamos la instancia de turnoDTO para devolver
			AppointmentDTO appointmentDTOtoReturn = new AppointmentDTO();
			appointmentDTOtoReturn.setId(appointment.getId());
			appointmentDTOtoReturn.setPatient_id(appointment.getPatient().getId());
			appointmentDTOtoReturn.setDentist_id(appointment.getDentist().getId());
			appointmentDTOtoReturn.setDate(appointment.getDate().toString());
			
			appointmentDTO = Optional.of(appointmentDTOtoReturn);
			return appointmentDTO;
		}else {
			throw new ResourceNotFoundException("No se encontró un turno con id: " + id);
		}
		
	}

	@Override
	public AppointmentDTO update(AppointmentDTO appointmentDTO) throws ResourceNotFoundException{
		
		//chequeo que el turno a actualizar exista
		if(appointmentRepository.findById(appointmentDTO.getId()).isPresent()) {
			//bsucar la entidad en la BD 
			Optional<Appointment> appointmentEntity = appointmentRepository.findById(appointmentDTO.getId());
			
			//instanciamos paciente
			Patient patientEntity = new Patient();
			patientEntity.setId(appointmentDTO.getPatient_id());
			
			//instanciar una entidad dentist
			Dentist dentistEntity = new Dentist();
			dentistEntity.setId(appointmentDTO.getDentist_id());
			
			//seteamos el paciente y el odontologo a nuestra entidad turno
			appointmentEntity.get().setPatient(patientEntity);
			appointmentEntity.get().setDentist(dentistEntity);
			
			//convertir el string del turnoDto a LocalDate
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(appointmentDTO.getDate(), formatter);
			
			//seteamos la fecha
			appointmentEntity.get().setDate(date); 
			
			//persistir a la BD
			appointmentRepository.save(appointmentEntity.get());
			
			//vamos a trabajar sobre el DTO a devolver
			AppointmentDTO appointmentDTOToReturn = new AppointmentDTO();
			appointmentDTOToReturn.setId(appointmentEntity.get().getId());
			appointmentDTOToReturn.setPatient_id(appointmentEntity.get().getPatient().getId());
			appointmentDTOToReturn.setDentist_id(appointmentEntity.get().getDentist().getId());
			appointmentDTOToReturn.setDate(appointmentEntity.get().getDate().toString());
			
			return appointmentDTOToReturn;
		}else {
			throw new ResourceNotFoundException("No se pudo actualizar el turno");
		}
		
		
		
	}

	@Override
	public Optional<AppointmentDTO> delete(Long id) throws ResourceNotFoundException {
		//vamos a buscar la entidad por id en la BD y guardarla en un opcional
		Optional<Appointment> appointmentToLookFor = appointmentRepository.findById(id);
		Optional<AppointmentDTO> appointmentDTO;
		if(appointmentToLookFor.isPresent()) {
			//recuperamos el turno que se encontró y lo guardamos en una variable turno 
			Appointment appointment = appointmentToLookFor.get(); 
			
			//vamos a devolver un DTO entonces trabajamos sobre sobre ese DTO a devolver
			
			AppointmentDTO appointmentDTOToReturn = new AppointmentDTO();
			appointmentDTOToReturn.setId(appointment.getId());
			appointmentDTOToReturn.setDentist_id(appointment.getDentist().getId());
			appointmentDTOToReturn.setPatient_id(appointment.getPatient().getId());
			appointmentDTOToReturn.setDate(appointment.getDate().toString());
			
			appointmentDTO = Optional.of(appointmentDTOToReturn);
			
			return  appointmentDTO;
		}else {
			//vamos a lanzar la excepcion 
			throw new ResourceNotFoundException("No existe el turno con id: " + id);
		}
		
	}

	@Override 
	public List<AppointmentDTO> findAll() {
		//VAMOS A TRAER las entidades de la BD y la vamos a guardar en una lista
		List<Appointment> appointments =  appointmentRepository.findAll();
		
		//vamos a crear una lista vacia de turnos DTO
		
		List<AppointmentDTO> appointmentDTO = new ArrayList<>();
		for(Appointment appointment : appointments) {
			appointmentDTO.add(new AppointmentDTO(appointment.getId(), appointment.getDentist().getId(), 
								   appointment.getPatient().getId(), appointment.getDate().toString()));
		}
		return appointmentDTO;
	}

  
}
