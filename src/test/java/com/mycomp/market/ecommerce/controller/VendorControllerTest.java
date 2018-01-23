package com.mycomp.market.ecommerce.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mycomp.market.ecommerce.service.VendorService;
import com.mycomp.market.ecommerce.util.StatusEnum;

@RunWith(MockitoJUnitRunner.class)
public class VendorControllerTest {

	// @Autowired
	private MockMvc mockMvc;

	// @MockBean
	@Mock
	private VendorService vendorService;

	@InjectMocks
	private VendorController vendorController;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
	}

	@Test
	public void claimOrderTest() throws Exception {
		when(this.vendorService.claimOrder("SMARTBUY765A", "AAB8765678765456430"))
				.thenReturn("The order AAB8765678765456430 is accepted by SMARTBUY765A");

		MvcResult result = this.mockMvc
				.perform(put("/assignOrder/SMARTBUY765A/AAB8765678765456430").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		assertThat(result.getResponse().getContentAsString())
				.isEqualTo("The order AAB8765678765456430 is accepted by SMARTBUY765A");
	}

	@Test
	public void alterProductAvailabilityStatusTest() throws Exception {
		when(this.vendorService.alterProductAvailabilityStatus("UCBT2234", StatusEnum.NOTAVAILABLE.name()))
				.thenReturn("Status altered for product UCBT2234");

		// MvcResult result =
		this.mockMvc.perform(put("/productStatus/UCBT2234/notavailable").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		// assertThat(result.getResponse().getContentAsString()).isEqualTo("Status
		// altered for product UCBT2234");
	}

}
