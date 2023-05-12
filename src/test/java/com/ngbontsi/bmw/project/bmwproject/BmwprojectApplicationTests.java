package com.ngbontsi.bmw.project.bmwproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngbontsi.bmw.project.bmwproject.controller.MainController;
import com.ngbontsi.bmw.project.bmwproject.model.Vehicle;
import com.ngbontsi.bmw.project.bmwproject.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@WebMvcTest(MainController.class)
class BmwprojectApplicationTests {

	@Test
	void contextLoads() {
	} @Autowired
	private MockMvc mockMvc;
	@MockBean
	private VehicleRepository vehicleRepository;
	@Autowired
	private ObjectMapper objectMapper;
	Vehicle firstCar = new Vehicle(1l,"firstCar","testing",1987);
	Vehicle secondCar = new Vehicle(2l,"secondCar","testing",1987);
	Vehicle thirdCar = new Vehicle(3l,"thirdCar","testing",1987);
	Vehicle forthCar = new Vehicle(4l,"forthCar","testing",1987);


	@Test
	public void getAllCars_success() throws Exception {
		ArrayList<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(firstCar);
		vehicles.add(secondCar);
		vehicles.add(thirdCar);
		vehicles.add(forthCar);


		Mockito.when(vehicleRepository.findAll()).thenReturn(vehicles);

		mockMvc.perform(MockMvcRequestBuilders
						.get("/vehicle")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(4)))
				.andExpect((ResultMatcher) jsonPath("$[2].name", is("secondCar")));

	}
	

}
