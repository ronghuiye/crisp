package com.ryan.crisp.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ryan.crisp.entity.Order;

@ExtendWith(MockitoExtension.class)
public class CsvProcessorTest {

	@InjectMocks
    private CsvProcessor csvProcessor;
	
	@Test
	public void testTransformToOrder() throws Exception {
		
		HashMap<String, Integer> columnOrder = new HashMap<>();
		columnOrder.put("orderId", 0);
		columnOrder.put("year", 1);
		columnOrder.put("month", 2);
		columnOrder.put("day", 3);
		columnOrder.put("productId", 4);
		columnOrder.put("productName", 5);
		columnOrder.put("quantity", 6);
		csvProcessor.setColumnOrder(columnOrder);
		String[] line = new String[] {"1001","2017","12","12","P-10002","Iceberg lettuce","500.00"};
		Order result = csvProcessor.transformToOrder(line);
		
		assertThat(result).isNotNull();
		assertThat(result.getOrderId()).isEqualTo(1001);
		assertThat(result.getYear()).isEqualTo("2017");
		assertThat(result.getMonth()).isEqualTo("12");
		assertThat(result.getDay()).isEqualTo("12");
		assertThat(result.getProductId()).isEqualTo("P-10002");
		assertThat(result.getProductName()).isEqualTo("Iceberg lettuce");
		assertThat(result.getQuantity()).isEqualTo(BigDecimal.valueOf(500.0));
		assertThat(result.getUnit()).isEqualTo("kg");
	}
}
