package com.sample.main.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Order {
	
	
	long orderId;
	
	@NotBlank
	String totalAmt;
	
	@NotBlank
	String interest;
	
	@NotBlank
	String basePrice;

	@NotBlank
	@Size(min=3,max=50)
	String borrowerName;
	
	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}
	

}
