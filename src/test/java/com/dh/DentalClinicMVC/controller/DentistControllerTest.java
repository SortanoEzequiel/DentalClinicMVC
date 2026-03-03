package com.dh.DentalClinicMVC.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.dh.DentalClinicMVC.service.IDentistService;
import com.dh.DentalClinicMVC.entity.Dentist;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DentistControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private IDentistService dentistService;
	
	
	public void datLoad() {
		Dentist dentist = new Dentist();
		dentist.setRegistration(12345);
		dentist.setName("Juan");
		dentist.setLastName("Nuñez");
		dentistService.save(dentist);
	}
	@Test 
	@Order(2)
	public void testGetDentistById() throws Exception {
		 datLoad();
		 mockMvc.perform(get("/odontologos/1")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.registration").value("12345"))
		.andExpect(jsonPath("$.name").value("Juan"))
		.andExpect(jsonPath("$.lastName").value("Nuñez"));
		
	}
	
	@Test
	@Order(3)
	public void testPostDentist() throws Exception {
		String dentistToSave = "{\"registration\": \"1234\", \"name\": \"Juan\", \"lastName\": \"Perez\"}";
		mockMvc.perform(post("/odontologos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(dentistToSave)
				.accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		    	.andExpect(jsonPath("$.registration").value("1234"))
				.andExpect(jsonPath("$.name").value("Juan"))
				.andExpect(jsonPath("$.lastName").value("Perez"));
	}
	
	@Test
	@Order(1)
	public void testAllDentist() throws Exception {
		mockMvc.perform(get("/odontologos"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("[]"));
				
	}
}
