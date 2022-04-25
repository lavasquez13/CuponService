package com.cupon.api.process;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.cupon.api.process.impl.CuponResponse;

public interface CuponProcess {
	
	public CuponResponse getCuponProcess(String[] item_ids, double amount) throws Exception ;

	public List<Entry<String, Integer>> getTopProcess() throws Exception;

}
