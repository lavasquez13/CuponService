package com.cupon.api.controller.impl;

import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cupon.api.controller.CuponController;
import com.cupon.api.process.CuponProcess;
import com.cupon.api.process.impl.CuponResponse;

@RestController
public class CuponControllerImpl implements CuponController{
	
	@Autowired
	CuponProcess cuponProcess;
	
	@PostMapping(value="/coupon", produces = {"application/json"})
	public ResponseEntity<CuponResponse> getCupon(String[] item_ids, int amount ) {
		try {
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(cuponProcess.getCuponProcess(item_ids, amount));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
	}
	
	@GetMapping("/top")
	public ResponseEntity<List<Entry<String, Integer>>> getTop(){
		try {
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(cuponProcess.getTopProcess());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
			return ResponseEntity.internalServerError().build();

		}
	}
}
