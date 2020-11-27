package com.ryan.crisp.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.ryan.crisp.entity.Order;

@Service("CSV")
public class CsvProcessor implements FileProcessor{

	private static final Logger logger = LogManager.getLogger(CsvProcessor.class);
	private static final String UNIT="kg";
	
	private List<String> validators;
	private HashMap<String, Integer> columnOrder;
	
	@Override
	public void process(MultipartFile file) throws IOException {
		
		Reader reader = new InputStreamReader(file.getInputStream());
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        String fileName = new SimpleDateFormat("yyyyMMddHHmm'.csv'").format(new Date());
        CSVWriter writer = new CSVWriter(new FileWriter(fileName));
        writeHeader(writer);
        
        String[] line;
		while((line = csvReader.readNext()) != null) {
			if(line.length < validators.size()) {
				logger.error(String.join(",", line));
				logger.error("Error reason: less fields than expected!");
				continue;
			}
			
			boolean isValid = true;
			StringBuilder reason = new StringBuilder();
			for(int i = 0; i < validators.size(); i++) {
				if(!line[i].matches(validators.get(i))) {
					isValid=false;
					reason.append("Field : " + line[i] + " failed validation.");
				}
			}
			if(!isValid) {
				logger.error(String.join(",", line));
				logger.error("Error reason: " + reason.toString());
			}else {
				writer.writeNext(transformToOrder(line).toStringArray());
			}
			
		}

	    writer.close();
		reader.close();
	    csvReader.close();
	}
	
	public Order transformToOrder(String[] line) {
		
		Order order = new Order();
		order.setOrderId(Integer.valueOf(line[columnOrder.get("orderId")]));
		order.setYear(line[columnOrder.get("year")]);
		order.setMonth(line[columnOrder.get("month")]);
		order.setDay(line[columnOrder.get("day")]);
		order.setProductId(line[columnOrder.get("productId")]);
		order.setProductName(line[columnOrder.get("productName")]);
		order.setQuantity(BigDecimal.valueOf(Double.parseDouble(line[columnOrder.get("quantity")])));
		order.setUnit(UNIT);
		
		return order;
	}

	private void writeHeader(CSVWriter writer) {
		String[] header = new String[] {"OrderId","OrderDate","ProductId","ProductName","Quantity","Unit"};
		writer.writeNext(header);
	}
	
	@Override
	public void setValidators(List<String> validators) {
		this.validators=validators;
	}

	@Override
	public void setColumnOrder(HashMap<String, Integer> columnOrder) {
		this.columnOrder = columnOrder;
	}

}
