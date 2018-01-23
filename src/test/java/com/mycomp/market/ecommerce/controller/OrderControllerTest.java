package com.mycomp.market.ecommerce.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.mycomp.market.ecommerce.config.CustomProperties;
import com.mycomp.market.ecommerce.service.OrderService;
import com.mycomp.market.ecommerce.util.MediaJsonHelper;
import com.mycomp.market.ecommerce.util.beans.PlaceOrderBean;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private OrderService orderService;
	
	@Mock
	private CustomProperties customProperties;
	
	@InjectMocks
	private OrderController orderController;
	
	@Before
    public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(orderController)                               
                .build();
	}

	@Test
	public void placeOrderTest() throws Exception {
		PlaceOrderBean orderBean = getOrderBean();
		
		when(this.customProperties.getMaxQuantityPerOrder()).thenReturn(3);

		this.mockMvc.perform(post("/order").accept(MediaType.APPLICATION_JSON).content(MediaJsonHelper.json(orderBean))
				.contentType(MediaJsonHelper.contentType)).andExpect(status().isCreated());
	}

	@Test
	public void trackOrderTest() throws Exception {
		when(this.orderService.getCurrentStatusFor("AAB8765678765456430")).thenReturn("Order_Received");

		MvcResult result = this.mockMvc
				.perform(get("/orderStatus/AAB8765678765456430").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(302)).andReturn();

		assertThat(result.getResponse().getContentAsString()).contains("Order_Received");
	}

	@Test
	public void cancelOrderTest() throws Exception {
		when(this.orderService.cancelOrderFor("AAB8765678765456430")).thenReturn("Order_Cancelled");

		MvcResult result = this.mockMvc
				.perform(put("/cancelOrder/AAB8765678765456430").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		assertThat(result.getResponse().getContentAsString()).contains("Order_Cancelled");
	}

	@Test
	public void updateOrderStatusTest() throws Exception {
		when(this.orderService.updateOrderStatus("AAB8765678765456430", "shipped"))
				.thenReturn("Status updated successfully for order AAB8765678765456430 to shipped");

		MvcResult result = this.mockMvc
				.perform(put("/updateOrderStatus/AAB8765678765456430/shipped").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		assertThat(result.getResponse().getContentAsString()).contains("shipped");
	}

	private PlaceOrderBean getOrderBean() {
		PlaceOrderBean orderBean = new PlaceOrderBean();
		orderBean.setUsername("RRajesh.kumar");
		orderBean.setProductCode(new String[] { "CUTLERYPREMIUM", "SMALLCHAIR" });
		orderBean.setQuantity(1);
		return orderBean;
	}

}
