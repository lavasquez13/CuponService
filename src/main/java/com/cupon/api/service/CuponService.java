package com.cupon.api.service;

import java.util.Optional;

import com.cupon.api.vo.PeticionVO;

public interface CuponService {
	
	PeticionVO save(PeticionVO greeting);
	Optional<PeticionVO> findAllLastOne();
    
}
