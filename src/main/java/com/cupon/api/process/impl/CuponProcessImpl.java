package com.cupon.api.process.impl;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import com.cupon.api.client.MeliClient;
import com.cupon.api.process.CuponProcess;
import com.cupon.api.service.CuponService;
import com.cupon.api.vo.PeticionVO;
import com.cupon.api.vo.ProductoVO;


@Component
public class CuponProcessImpl implements CuponProcess{

	private String uri = "https://api.mercadolibre.com/items/";
	
	@Autowired
	CuponService cuponService;
	@Autowired
	MeliClient meliClient;
	
	@Override
	public  CuponResponse getCuponProcess(String[] item_ids, double amount) throws Exception {
		double priceUsed =0;
		try {
			final PeticionVO peticion = new PeticionVO();
			peticion.setValorCupon(amount);
			peticion.setFecha(new Date());
			
			final List<ProductoVO> todosProductos = new ArrayList<ProductoVO>();
			
			for (String item : item_ids) {
				final JSONObject objMELI = meliClient.ClientItem(item);
				ProductoVO producto = new ProductoVO();
				producto.setProducto(item);
				producto.setPrecio(objMELI.getDouble("price"));
				producto.setPeticion(peticion);
				todosProductos.add(producto);
			}
			
			Collections.sort(todosProductos, new Comparator<ProductoVO>() {
				@Override
				public int compare(ProductoVO o1, ProductoVO o2) {
					if(o1.getPrecio() < o2.getPrecio())
						return -1;
					if(o1.getPrecio() > o2.getPrecio())
						return 1;
					
					return 0;
				}
			});
			
			final List<ProductoVO> productosComprar = new ArrayList<ProductoVO>();
			for (ProductoVO producto : todosProductos) {
				if(priceUsed + producto.getPrecio() <= amount) {
					priceUsed += producto.getPrecio();
					productosComprar.add(producto);
				}else {
					break;
				}
			}
			peticion.setProductoVO(productosComprar);
			List<String> items = productosComprar.stream().map(ProductoVO::getProducto).collect(Collectors.toList());
			CuponResponse response = new CuponResponse(items, priceUsed);
			cuponService.save(peticion);
			
			return response;
			
		} catch (Exception e) {
			System.err.println(e);
			throw e;	
		}
		
	}
	
	@Override
	public List<Map.Entry<String,Integer>> getTopProcess() throws Exception {
		List<Map.Entry<String,Integer>> topItems = new ArrayList<>();
		Optional<PeticionVO> peticionVO = cuponService.findAllLastOne();
		if(peticionVO.isPresent()) {
			try {
				for (ProductoVO productoVO : peticionVO.get().getProductoVO()) {
					final String producto = productoVO.getProducto();
					final JSONObject jsonObject= meliClient.ClientItem(producto);
					topItems.add(new AbstractMap.SimpleEntry<>(producto, jsonObject.getInt("sold_quantity")));
				}	
				Collections.sort(topItems, new Comparator<Entry<String, Integer>>() {
					@Override
					public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
						if(o1.getValue() < o2.getValue())
							return 1;
						if(o1.getValue() > o2.getValue())
							return -1;
						
						return 0;
					}

				});
			} catch (Exception e) {
				System.err.println(e);
				throw e;	
			}
		}
		return topItems.stream().limit(5).collect(Collectors.toList());
	}
	

}
