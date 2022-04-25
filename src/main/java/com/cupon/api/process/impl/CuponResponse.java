package com.cupon.api.process.impl;

import java.util.ArrayList;
import java.util.List;

public class CuponResponse {
	
	private ArrayList<String> item_ids;
	private double amount;

	public ArrayList<String> getItem_ids() {
		return item_ids;
	}

	public void setItem_ids(ArrayList<String> item_ids) {
		this.item_ids = item_ids;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public CuponResponse(List<String> item_ids, double amount) {
		super();
		this.item_ids = new ArrayList<String>(item_ids);
		this.amount = amount;
	}
	
}
