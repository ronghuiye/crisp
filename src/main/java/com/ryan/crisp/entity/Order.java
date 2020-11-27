package com.ryan.crisp.entity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Order {

	private Integer orderId;
	private String year;
	private String month;
	private String day;
    private Date orderDate;
    private String productId;
    private String productName;
    private BigDecimal quantity;
    private String unit;
    
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Date getOrderDate() {
		if(orderDate != null) {
			return orderDate;
		}else {
			SimpleDateFormat formatter=new SimpleDateFormat("MM/dd/yyyy");
			try {
				orderDate = formatter.parse(month+"/"+day+"/"+year);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Override
	public String toString() {
		return orderId + "," + orderDate + "," + productId + "," + productName + "," + quantity + "," + unit;
	}
	public String[] toStringArray() {
		return new String[] {String.valueOf(orderId), getOrderDate().toString(), productId, productName, quantity.toString(), unit};
	}
}
