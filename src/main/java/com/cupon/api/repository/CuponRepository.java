package com.cupon.api.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cupon.api.vo.PeticionVO;

@Repository
public interface CuponRepository extends CrudRepository<PeticionVO, Long> {

//	@Query(value = "select pe from PeticionVO pe order by fecha desc ")
	public Page<PeticionVO> findAll(Pageable pageable);
	
	
}
