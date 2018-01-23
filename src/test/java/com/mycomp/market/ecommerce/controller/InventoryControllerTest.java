package com.mycomp.market.ecommerce.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.mycomp.market.ecommerce.service.InventoryService;
import com.mycomp.market.ecommerce.util.MediaJsonHelper;
import com.mycomp.market.ecommerce.util.beans.CategoryBean;
import com.mycomp.market.ecommerce.util.beans.ProductBean;

@RunWith(SpringRunner.class)
@WebMvcTest(value = InventoryController.class)
public class InventoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InventoryService inventoryService;

	@Test
	public void createCategoryTest() throws Exception {
		CategoryBean category = getCatalogBean();

		MvcResult response = this.mockMvc.perform(post("/category").accept(MediaType.APPLICATION_JSON)
				.content(MediaJsonHelper.json(category)).contentType(MediaJsonHelper.contentType))
				.andExpect(status().isCreated()).andReturn();

		assertEquals(response.getResponse().getStatus(), HttpStatus.CREATED.value());
	}

	@Test
	public void updateProductQuantityTest() throws Exception {

		when(this.inventoryService.updateProductQuantity("CUTLERYPREMIUM", 100)).thenReturn("Updated");

		MvcResult result = this.mockMvc.perform(put("/product/CUTLERYPREMIUM/100").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		assertThat(result.getResponse().getContentAsString()).isEqualTo("Updated");
	}

	private CategoryBean getCatalogBean() {
		CategoryBean category = new CategoryBean();
		category.setName("KITCHEN Items");
		category.setCategoryCode("KITCHENITEMS");
		category.setDescription("Items used for daily kitchen activities");
		return category;
	}

	@SuppressWarnings("unused")
	private ProductBean getProductBean() {
		ProductBean product = new ProductBean();
		product.setName("Cutlery");
		product.setDescription("Cutlery set for kitchen");
		product.setPrice(1234);
		product.setProductCode("CUTLERYPREMIUM");
		product.setCategoryCode("KITCHENITEMS");
		return null;
	}

}
