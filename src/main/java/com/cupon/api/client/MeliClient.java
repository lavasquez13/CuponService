package com.cupon.api.client;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MeliClient {

	private String uri = "https://api.mercadolibre.com/items/";

	public JSONObject ClientItem(String item_id) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		try {
			String objMELI = restTemplate.getForObject(uri+item_id, String.class);
			JSONObject jsonObject = new JSONObject(objMELI);
			return jsonObject;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	} 
}
