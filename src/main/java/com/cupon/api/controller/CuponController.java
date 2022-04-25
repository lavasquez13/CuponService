package com.cupon.api.controller;

import java.util.List;
import java.util.Map.Entry;

import org.springframework.http.ResponseEntity;

import com.cupon.api.process.impl.CuponResponse;

public interface CuponController {

	public ResponseEntity<CuponResponse> getCupon(String[] item_ids, int amount);
	
	public ResponseEntity<List<Entry<String, Integer>>> getTop();

}
