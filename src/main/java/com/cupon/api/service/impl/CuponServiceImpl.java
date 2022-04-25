package com.cupon.api.service.impl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cupon.api.repository.CuponRepository;
import com.cupon.api.service.CuponService;
import com.cupon.api.vo.PeticionVO;

@Service
public class CuponServiceImpl implements CuponService{

	@Autowired
	private CuponRepository cuponRepository;
	
	@Override
	public Optional<PeticionVO> findAllLastOne() {
		// TODO Auto-generated method stub
		Page<PeticionVO> pagePeticion=cuponRepository.findAll(PageRequest.of(0, 1, Sort.by(Direction.DESC,"fecha")));
		return pagePeticion.get().findFirst();
		}

	@Override
	public PeticionVO save(PeticionVO peticionVO) {
		// TODO Auto-generated method stub
		peticionVO=cuponRepository.save(peticionVO);
		System.out.println(peticionVO);
		return peticionVO;
	}

}
